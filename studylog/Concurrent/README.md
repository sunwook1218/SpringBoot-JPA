# 멀티스레드 프로그래밍

```java

int static count = 5;

main() {
  ExecutorService executor = new ThreadPoolExecutor(count, count, 60, TimeUnit.SECONDS, new LinkedBlokingQueue<>());
  CountDownLatch latch = new CountDownLatch(count);
  
  List<Future<String>> collect = Stream.generate(() -> {
    return executor.submit(() -> {
      String randomStayTask = randomStayTask();
      latch.countDown();
      return randomStayTask;
    });
  }).limit(count).collect(Collectors.toList());
  
  try {
    latch.await();
  } catch (Exception e) {
  }
  
  sysout("Await Ends");
  
  collect.forEach(future -> {
    try {
      sysout(future.get());
    } catch (Exception e) {
    }
  });
}
```
