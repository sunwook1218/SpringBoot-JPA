# 추상화

## Concept

팀 내 SpringBoot로 올려진 프로젝트에 캐시 및 이벤트핸들링 라이브러리를 추가하려고 한다. 이때 ApacheIgnite VS Redis Cache 중 선택하여 구현하여야 하는데, 

Apache Ignite로 구현하고 이후에 Redis Cache로 마이그레이션을 할 가능성이 있다는 전제 하여 디자인 가능하게

1. 해당 프로젝트에서 사용할 Cache 기능을 추상화하여 인터페이스로 정의한다.

예를들어 EMS 프로젝트라면, interface IEMSCache 를 정의하고 필수 구현 function을 기술 한 뒤, Ignite를 Bean으로 사용하는 EMSCache를 Bean으로 만들어 사용한다.
2. Redis 마이그레이션

이후에 노후화 이슈 혹은 Redis에서만 제공되는 Util을 필수적용하기 위해 ApacheIgnite -> RedisCache로의 마이그레이션이 필요하다해도, EMSCache의 구현체를 변경해주기만 하면 실제로
프로젝트에서는 IEMSCache 인터페이스의 Bean을 사용하고 있기 때문에, 코드의 수정 없이 구현체의 교체만으로 손쉡게 마이그레이션을 처리 할 수 있다. 

3. Config package 전략

- configuration
- ㄴ cache
-   IEMSCache
-   EMSCache
-   ㄴignite
-     IgniteConfiguration
-   ㄴ Redis
-     RedisConfiguration

