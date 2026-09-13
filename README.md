# ExpoScanner

Selamlar, Turkhackteam için hazırladığım basit ama hızlı bir port tarama aracı (Port Scanner). 
Java'da thread (iş parçacığı) kullanarak tarama hızını bayağı arttırdım. Klasik for döngüsüyle taramaktan çok daha hızlı ve pratik.

## Nasıl Kullanılır?
Bilgisayarınızda java kurulu olmalı.

```bash
javac src/PortScanner.java
java src.PortScanner
```

Terminalde sana hedef IP'yi, nereden nereye kadar tarayacağını ve thread sayısını (hız) soracak. Hız için 50-100 arası ideal, bilgisayarına ve internetine göre arttırıp azaltabilirsin.

Kendini geliştirmek isteyenler kodları alıp istediği gibi kurcalayabilir. Yeni başladığım için çok karmaşık yapılara girmeden temel düzeyde tutmaya çalıştım.

-TurkHackTeam
