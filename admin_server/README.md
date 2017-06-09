## 用途
监控 Spring Boot 实例

## 缺点

没有时间序列的监控数据，只有对孤立实例的监控快照

## 更优方案
`Jolokia` + `Telegraf` + `InfluxDB` + `Grafana`(or `Chronograf`) + `Kapacitor`

