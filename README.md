
good 

```bash
ab -n 15000 -c 100 -k http://127.0.0.1:8080/heartbeat
This is ApacheBench, Version 2.3 <$Revision: 1843412 $>
Copyright 1996 Adam Twiss, Zeus Technology Ltd, http://www.zeustech.net/
Licensed to The Apache Software Foundation, http://www.apache.org/

Benchmarking 127.0.0.1 (be patient)
Completed 1000 requests
Completed 2000 requests
Completed 3000 requests
Completed 4000 requests
Completed 5000 requests
Completed 6000 requests
Completed 7000 requests
Completed 8000 requests
Completed 9000 requests
Completed 10000 requests
Finished 10000 requests


Server Software:        
Server Hostname:        127.0.0.1
Server Port:            8080

Document Path:          /heartbeat
Document Length:        78 bytes

Concurrency Level:      100
Time taken for tests:   0.698 seconds
Complete requests:      10000
Failed requests:        0
Keep-Alive requests:    0
Total transferred:      1490000 bytes
HTML transferred:       780000 bytes
Requests per second:    14330.16 [#/sec] (mean)
Time per request:       6.978 [ms] (mean)
Time per request:       0.070 [ms] (mean, across all concurrent requests)
Transfer rate:          2085.15 [Kbytes/sec] received

Connection Times (ms)
              min  mean[+/-sd] median   max
Connect:        0    3   0.8      3       6
Processing:     1    4   0.9      4      11
Waiting:        1    3   0.9      3      10
Total:          3    7   1.1      7      13

Percentage of the requests served within a certain time (ms)
  50%      7
  66%      7
  75%      8
  80%      8
  90%      8
  95%      9
  98%     10
  99%     10
 100%     13 (longest request)
```

bad

```bash
ab -n 15000 -c 100 -k http://127.0.0.1:8080/heartbeat
This is ApacheBench, Version 2.3 <$Revision: 1843412 $>
Copyright 1996 Adam Twiss, Zeus Technology Ltd, http://www.zeustech.net/
Licensed to The Apache Software Foundation, http://www.apache.org/

Benchmarking 127.0.0.1 (be patient)
Completed 1000 requests
Completed 2000 requests
Completed 3000 requests
Completed 4000 requests
Completed 5000 requests
Completed 6000 requests
Completed 7000 requests
Completed 8000 requests
Completed 9000 requests
Completed 10000 requests
Finished 10000 requests


Server Software:        
Server Hostname:        127.0.0.1
Server Port:            8080

Document Path:          /heartbeat
Document Length:        78 bytes

Concurrency Level:      100
Time taken for tests:   1.914 seconds
Complete requests:      10000
Failed requests:        0
Keep-Alive requests:    0
Total transferred:      1490000 bytes
HTML transferred:       780000 bytes
Requests per second:    5223.31 [#/sec] (mean)
Time per request:       19.145 [ms] (mean)
Time per request:       0.191 [ms] (mean, across all concurrent requests)
Transfer rate:          760.03 [Kbytes/sec] received

Connection Times (ms)
              min  mean[+/-sd] median   max
Connect:        0    2  18.7      0     455
Processing:     3   15  41.5      9     459
Waiting:        3   15  41.2      9     459
Total:          7   17  45.1     10     461

Percentage of the requests served within a certain time (ms)
  50%     10
  66%     12
  75%     13
  80%     14
  90%     22
  95%     28
  98%     55
  99%    456
 100%    461 (longest request)
```

worst

```bash
ab -n 15000 -c 100 -k http://127.0.0.1:8080/heartbeat
This is ApacheBench, Version 2.3 <$Revision: 1843412 $>
Copyright 1996 Adam Twiss, Zeus Technology Ltd, http://www.zeustech.net/
Licensed to The Apache Software Foundation, http://www.apache.org/

Benchmarking 127.0.0.1 (be patient)
Completed 1000 requests
Completed 2000 requests
Completed 3000 requests
Completed 4000 requests
Completed 5000 requests
Completed 6000 requests
Completed 7000 requests
Completed 8000 requests
Completed 9000 requests
Completed 10000 requests
Finished 10000 requests


Server Software:        
Server Hostname:        127.0.0.1
Server Port:            8080

Document Path:          /heartbeat
Document Length:        78 bytes

Concurrency Level:      100
Time taken for tests:   13.961 seconds
Complete requests:      10000
Failed requests:        0
Keep-Alive requests:    0
Total transferred:      1490000 bytes
HTML transferred:       780000 bytes
Requests per second:    716.28 [#/sec] (mean)
Time per request:       139.611 [ms] (mean)
Time per request:       1.396 [ms] (mean, across all concurrent requests)
Transfer rate:          104.22 [Kbytes/sec] received

Connection Times (ms)
              min  mean[+/-sd] median   max
Connect:        0  135 1310.4      4   13176
Processing:     0    4   1.0      4      11
Waiting:        0    4   1.0      4      11
Total:          0  140 1310.2      8   13180

Percentage of the requests served within a certain time (ms)
  50%      8
  66%      8
  75%      9
  80%      9
  90%     10
  95%     11
  98%     13
  99%  13172
 100%  13180 (longest request)
```

![](netty_rector_2020.png)

references
--

- https://projectreactor.io/docs/netty/release/reference/index.html#http-server
- https://projectreactor.io/docs/core/release/reference/#_blocking_can_be_wasteful