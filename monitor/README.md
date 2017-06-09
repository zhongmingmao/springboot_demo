# 1. Spring Boot Jolokia

```
<dependency>
    <groupId>org.jolokia</groupId>
    <artifactId>jolokia-core</artifactId>
</dependency>
```

[Using Jolokia for JMX over HTTP](http://docs.spring.io/spring-boot/docs/current-SNAPSHOT/reference/htmlsingle/#production-ready-jolokia)

# 2 Influxdata

[Influxdata Docs](http://docs.influxdata.com/)

## 2.1 InfluxData Architecture

![InfluxData Architecture](https://www.influxdata.com/wp-content/uploads/Markitecture-v8-WHITE-1.jpg)

## 2.2 Telegraf

Telegraf is a plugin-driven server agent for `collecting & reporting metrics`, and is the `first piece` of the **`TICK stack`**.

### 2.2.1 Install

```
$ brew update
$ brew install telegraf
$ ln -sfv /usr/local/opt/telegraf/*.plist ~/Library/LaunchAgents
$ launchctl load ~/Library/LaunchAgents/homebrew.mxcl.telegraf.plist
```

### 2.2.2 Config & Restart

```
$ telegraf -sample-config -input-filter cpu:mem:disk:diskio:system:jolokia -output-filter influxdb > telegraf.conf
$ cp telegraf.conf /usr/local/etc/telegraf.conf
$ brew services restart telegraf
```

### 2.2.3 telegraf.conf

```
[[inputs.jolokia]]
    context = "/jolokia/"
    [[inputs.jolokia.servers]]
        name = "as-server-01"
        host = "127.0.0.1"
        port = "8080"

    [[inputs.jolokia.metrics]]
        name = "heap_memory_usage"
        mbean  = "java.lang:type=Memory"
        attribute = "HeapMemoryUsage"
    
    
    [[inputs.jolokia.metrics]]
        name = "thread_count"
        mbean  = "java.lang:type=Threading"
        attribute = "TotalStartedThreadCount,ThreadCount,DaemonThreadCount,PeakThreadCount"
    
    [[inputs.jolokia.metrics]]
        name = "class_count"
        mbean  = "java.lang:type=ClassLoading"
        attribute = "LoadedClassCount,UnloadedClassCount,TotalLoadedClassCount"

[[outputs.influxdb]]
    urls = ["http://localhost:8086"]
    database = "telegraf"
    retention_policy = ""
    write_consistency = "any"
    timeout = "5s"
```

### 2.2.4 Results (Output : InfluxDB)

```
$ influx
Connected to http://localhost:8086 version v1.2.2
InfluxDB shell version: v1.2.2
> SHOW DATABASES;
name: databases
name
----
telegraf
_internal

> USE telegraf
Using database telegraf

> SHOW MEASUREMENTS
name: measurements
name
----
cpu
disk
jolokia
mem
processes
swap
system

> SHOW FIELD KEYS
name: cpu
fieldKey         fieldType
--------         ---------
usage_guest      float
usage_guest_nice float
usage_idle       float
usage_iowait     float
usage_irq        float
usage_nice       float
usage_softirq    float
usage_steal      float
usage_system     float
usage_user       float
......
name: jolokia
fieldKey                             fieldType
--------                             ---------
class_count_LoadedClassCount         float
class_count_TotalLoadedClassCount    float
class_count_UnloadedClassCount       float
heap_memory_usage_committed          float
heap_memory_usage_init               float
heap_memory_usage_max                float
heap_memory_usage_used               float
thread_count_DaemonThreadCount       float
thread_count_PeakThreadCount         float
thread_count_ThreadCount             float
thread_count_TotalStartedThreadCount float
......

> SELECT usage_idle FROM cpu WHERE cpu = 'cpu-total' LIMIT 5
name: cpu
time                usage_idle
----                ----------
1496993940000000000 82.78342842967005
1496993950000000000 88.58070964517741
1496993960000000000 80.25
1496993970000000000 74.9624812406203
1496993980000000000 71.85703574106473
```

### 2.2.5 Telegraf Docs

1. [Installation](http://docs.influxdata.com/telegraf/v1.3/introduction/installation/#installation)
2. [Getting Started](http://docs.influxdata.com/telegraf/v1.3/introduction/getting_started/)

## 2.3 InfluxDB 

InfluxDB is a `time series database` built from the ground up to handle high write and query loads. It is the `second piece` of the **`TICK stack`**.

### 2.3.1 Install

```
$ brew update
$ brew install influxdb
$ ln -sfv /usr/local/opt/influxdb/*.plist ~/Library/LaunchAgents
$ launchctl load ~/Library/LaunchAgents/homebrew.mxcl.influxdb.plist
```

### 2.3.2 influxdb.conf

```
[admin]
    enabled = true
    bind-address = ":8083"
    
[http]
    enabled = true
    bind-address = ":8086"
```

### 2.3.3 Restart

```
$ brew services restart influxdb
```

### 2.3.4 Ops

```
$ influx -precision rfc3339 # timestamps in RFC3339 format (YYYY-MM-DDTHH:MM:SS.nnnnnnnnnZ)
Connected to http://localhost:8086 version v1.2.2
InfluxDB shell version: v1.2.2
> SHOW DATABASES
name: databases
name
----
telegraf
_internal

> USE telegraf
Using database telegraf

> SELECT usage_idle FROM cpu WHERE cpu = 'cpu-total' LIMIT 5
name: cpu
time                 usage_idle
----                 ----------
2017-06-09T07:39:00Z 82.78342842967005
2017-06-09T07:39:10Z 88.58070964517741
2017-06-09T07:39:20Z 80.25
2017-06-09T07:39:30Z 74.9624812406203
2017-06-09T07:39:40Z 71.85703574106473
```

### 2.3.5 InfluxDB Docs

1. [Installation](http://docs.influxdata.com/influxdb/v1.2/introduction/installation/#installation)
2. [Getting Started](http://docs.influxdata.com/influxdb/v1.2/introduction/getting_started/)


## 2.4 Chronograf

Chronograf is a `graphing and visualization application` for performing ad hoc exploration of `InfluxDB` data. 

**TBC**

## 2.5 Kapacitor 
Kapacitor is a `data processing framework` that makes it easy to `create alerts`, `run ETL jobs` and `detect anomalies`.

**TBC**

# 3. Grafana 

## 3.1 Install

```$xslt
$ brew update 
$ brew install grafana
```

## 3.2 grafana.ini

```
[server]
protocol = http
http_port = 3000
domain = localhost

[security]
admin_user = admin
admin_password = admin
```

## 3.3 Restart

```
brew services restart grafana
```

## 3.4 Example

![monitor](http://or9cl4687.bkt.clouddn.com/monitor.png)

## 3.5 Grafana Docs

1. [Installing on Mac](http://docs.grafana.org/installation/mac/)
1. [Using InfluxDB in Grafana](http://docs.grafana.org/features/datasources/influxdb/)


