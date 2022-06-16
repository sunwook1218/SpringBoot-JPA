# Quartz Scheduler 도입

- Config & Bean 설정
```java
    @Bean
    public Scheduler scheduler(Trigger trigger, JobDetail job, SchedulerFactoryBean factory)
            throws SchedulerException {

        Scheduler scheduler = factory.getScheduler();
//        scheduler.scheduleJob(job, trigger);
        scheduler.start();

        return scheduler;
    }
```
일반적인 환경을 기준으로 인스턴스 하나에 스케줄러 하나를 등록하여 사용한다고 가정.

추가로 job을 동적으로 add할때의 경우를 고려하여 scheduler를 start시키고 등록한 Job과 Trigger내용에 따라 scheduler Bean을 호출해 추가할 수 있게 구현하였다.

### 궁금한 점
[스프링부트Quartz가이드](https://www.baeldung.com/spring-quartz-schedule)

해당 링크에서는 JobDetail과 Trigger를 Bean으로 등록하여 등록하는 내용을 소개하고 있는데, JobDetail과 Trigger를 Bean으로 등록해서 얻는 이점은 무엇일까? Job구현체만 존재한다면 수동으로 add하는 게 더 낫지 않을까? 

- ManuallyAddedJobConfig.java
```java
@Component
public class ManuallyAddedJobConfig {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private Scheduler scheduler;

    @PostConstruct
    public void addJob() {

        JobBuilder jobBuilder = JobBuilder.newJob(SampleJob2.class);

        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("dataMap", "value");

        jobBuilder.usingJobData(jobDataMap);
        jobBuilder.withIdentity("Quartz manually added job", Scheduler.DEFAULT_GROUP);

        final JobDetail job = jobBuilder.build();

        final Trigger quartz_manually_added_trigger =
                TriggerBuilder.newTrigger()
                        .withIdentity("T", Scheduler.DEFAULT_GROUP)
//                        .forJob(job)
//                        .withSchedule(SimpleScheduleBuilder.simpleSchedule().repeatForever().withIntervalInMinutes(1))
                        .withSchedule(CronScheduleBuilder.cronSchedule("0 0/1 * * * ?"))
                        .build();

        try {

            scheduler.scheduleJob(job, quartz_manually_added_trigger);

        } catch (SchedulerException e) {
            logger.error("Manually added job Error", e);
        }

    }

}
```
CronExp으로 Schedule한 SampleJob2의 등록 코드이다.

