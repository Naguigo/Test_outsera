
## Executar teste de API

"mvn clean test"

## Executar relatorio

"allure serve target/allure-results"


## Executar teste de performance

"cd src/test/java"

"k6 run --out json=resultado.json performanceTest.js"


