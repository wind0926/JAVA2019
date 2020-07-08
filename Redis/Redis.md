
* [1.在分布式数据库中CAP原理CAP+BASE](#1在分布式数据库中cap原理capbase)
    * [CAP](#cap)
    * [经典CAP图](#经典cap图)
    * [Base](#base)
    * [分布式+集群简介](#分布式集群简介)
* [2.数据类型](#2数据类型)
    * [Redis字符串(String)](#redis字符串string)
      * [常用](#常用)
      * [案例](#案例)
    * [Redis列表(List)](#redis列表list)
      * [常用](#常用-1)
      * [案例](#案例-1)
    * [Redis集合(Set)](#redis集合set)
      * [常用](#常用-2)
      * [案例](#案例-2)
      * [数学集合类](#数学集合类)
        * [差集：sdiff](#差集：sdiff)
        * [交集：sinter](#交集：sinter)
        * [并集：sunion](#并集：sunion)
    * [Redis哈希(Hash)](#redis哈希hash)
      * [常用](#常用-3)
      * [案例](#案例-3)
    * [Redis有序集合Zset(sorted set)](#redis有序集合zsetsorted-set)
      * [常用](#常用-4)
      * [案例](#案例-4)
        * [zcard ：获取集合中元素个数](#zcard-：获取集合中元素个数)
        * [zcount ：获取分数区间内元素个数，zcount key 开始分数区间 结束分数区间](#zcount-：获取分数区间内元素个数，zcount-key-开始分数区间-结束分数区间)
        * [zrank： 获取value在zset中的下标位置](#zrank：-获取value在zset中的下标位置)
        * [zscore：按照值获得对应的分数](#zscore：按照值获得对应的分数)
* [3.谈下你对 Redis 的了解？](#3谈下你对-redis-的了解？)
* [4.Redis 一般都有哪些使用场景？](#4redis-一般都有哪些使用场景？)
        * [Redis 适合的场景](#redis-适合的场景)
        * [Redis 不适合的场景](#redis-不适合的场景)
* [5.Redis 为什么是单线程的？](#5redis-为什么是单线程的？)
* [6.Redis 为什么这么快？](#6redis-为什么这么快？)
* [7.什么是缓存穿透？怎么解决？](#7什么是缓存穿透？怎么解决？)
      * [解决办法](#解决办法)
* [8.什么是缓存雪崩？该如何解决？](#8什么是缓存雪崩？该如何解决？)
      * [解决办法](#解决办法-1)
* [9.怎么保证缓存和数据库数据的一致性？](#9怎么保证缓存和数据库数据的一致性？)
    * [1.分别处理](#1分别处理)
    * [2.高并发情况下](#2高并发情况下)
    * [3.基于订阅binlog的同步机制](#3基于订阅binlog的同步机制)
* [10.Redis 持久化有几种方式？](#10redis-持久化有几种方式？)
    * [RDB](#rdb)
    * [AOF](#aof)
      * [RDB 和 AOF 的区别：](#rdb-和-aof-的区别：)
* [11.Redis 怎么实现分布式锁？](#11redis-怎么实现分布式锁？)
* [12.Redis 淘汰策略有哪些？](#12redis-淘汰策略有哪些？)
* [13.Redis的原子性如何保证](#13redis的原子性如何保证)
* [14.Redis 有哪些架构模式讲讲各自的特点](#14redis-有哪些架构模式讲讲各自的特点)
    * [单机版](#单机版)
    * [主从复制](#主从复制)
    * [哨兵](#哨兵)
    * [集群（proxy 型）](#集群（proxy-型）)
    * [集群（直连型）](#集群（直连型）)
* [15.使用过Redis做异步队列么，你是怎么用的？有什么缺点？](#15使用过redis做异步队列么，你是怎么用的？有什么缺点？)
* [16.能不能生产一次消费多次呢？](#16能不能生产一次消费多次呢？)
    * [案例](#案例-5)
* [17.Redis相比memcached有哪些优势？](#17redis相比memcached有哪些优势？)
* [18.Redis如何存储复杂的数据？](#18redis如何存储复杂的数据？)
    * [复杂数据存储](#复杂数据存储)
      * [json存储](#json存储)
      * [hash存储](#hash存储)
      * [获取数据](#获取数据)
* [19.Redis的过期策略](#19redis的过期策略)
* [20.Redis底层数据结构](#20redis底层数据结构)
    * [1.简单动态字符串](#1简单动态字符串)
        * [①、常数复杂度获取字符串长度](#①、常数复杂度获取字符串长度)
        * [②、杜绝缓冲区溢出](#②、杜绝缓冲区溢出)
        * [③、减少修改字符串的内存重新分配次数](#③、减少修改字符串的内存重新分配次数)
        * [④、二进制安全](#④、二进制安全)
        * [⑤、兼容部分 C 字符串函数](#⑤、兼容部分-c-字符串函数)
        * [⑥、总结](#⑥、总结)
    * [2.链表](#2链表)
    * [3.字典](#3字典)
                                  * [key 的哈希值](#key-的哈希值)
                                                                    * [](#)
    * [4.跳跃表](#4跳跃表)
    * [5.整数集合](#5整数集合)
    * [6.压缩列表](#6压缩列表)
    * [7.总结](#7总结)


# 1.在分布式数据库中CAP原理CAP+BASE

### CAP

```
C:Consistency（强一致性）

A:Availability（可用性）

P:Partition tolerance（分区容错性）

注意：分布式架构的时候必须做出取舍。
一致性和可用性之间取一个平衡。多余大多数web应用，其实并不需要强一致性。因此牺牲C换取P，这是目前分布式数据库产品的方向

```

### 经典CAP图

```
CAP理论的核心是：一个分布式系统不可能同时很好的满足一致性，可用性和分区容错性这三个需求，
最多只能同时较好的满足两个。
因此，根据 CAP 原理将 NoSQL 数据库分成了满足 CA 原则、满足 CP 原则和满足 AP 原则三大类：
CA - 单点集群，满足一致性，可用性的系统，通常在可扩展性上不太强大。
CP - 满足一致性，分区容忍必的系统，通常性能不是特别高。
AP - 满足可用性，分区容忍性的系统，通常可能对一致性要求低一些。
```

![1585993993074](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/redis/1.png)



### Base

BASE就是为了解决关系数据库强一致性引起的问题而引起的可用性降低而提出的解决方案。

BASE其实是下面三个术语的缩写：
    基本可用（Basically Available）
    软状态（Soft state）
    最终一致（Eventually consistent）

它的思想是通过让系统放松对某一时刻数据一致性的要求来换取系统整体伸缩性和性能上改观。为什么这么说呢，缘由就在于大型系统往往由于地域分布和极高性能的要求，不可能采用分布式事务来完成这些指标，要想获得这些指标，我们必须采用另外一种方式来完成，这里BASE就是解决这个问题的办法

### 分布式+集群简介

分布式系统

分布式系统（distributed system）
 由多台计算机和通信的软件组件通过计算机网络连接（本地网络或广域网）组成。分布式系统是建立在网络之上的软件系统。正是因为软件的特性，所以分布式系统具有高度的内聚性和透明性。因此，网络和分布式系统之间的区别更多的在于高层软件（特别是操作系统），而不是硬件。分布式系统可以应用在在不同的平台上如：Pc、工作站、局域网和广域网上等。



简单来讲：
1分布式：不同的多台服务器上面部署不同的服务模块（工程），他们之间通过Rpc/Rmi之间通信和调用，对外提供服务和组内协作。

2集群：不同的多台服务器上面部署相同的服务模块，通过分布式调度软件进行统一的调度，对外提供服务和访问。



# 2.数据类型

| 数据类型 | 可以存储的值           | 操作                                                         |
| -------- | ---------------------- | ------------------------------------------------------------ |
| STRING   | 字符串、整数或者浮点数 | 对整个字符串或者字符串的其中一部分执行操作对整数和浮点数执行自增或者自减操作 |
| LIST     | 列表                   | 从两端压入或者弹出元素对单个或者多个元素进行修剪，只保留一个范围内的元素 |
| SET      | 无序集合               | 添加、获取、移除单个元素，检查一个元素是否存在于集合中，计算交集、并集、差集，从集合里面随机获取元素 |
| HASH     | 包含键值对的无序散列表 | 添加、获取、移除单个键值对，获取所有键值对，检查某个键是否存在 |
| ZSET     | 有序集合               | 添加、获取、删除元素，根据分值范围或者成员来获取元素，计算一个键的排名 |



### Redis字符串(String)

#### 常用

![1585994656165](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/redis/2.png)

![1585995177785](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/redis/11.png)

#### 案例

```java
set/get/del/append/strlen

Incr/decr/incrby/decrby,一定要是数字才能进行加减

getrange/setrange
```

getrange:获取指定区间范围内的值，类似between......and的关系
从零到负一表示全部

![1585994783366](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/redis/3.png)


setrange设置指定区间范围内的值，格式是setrange key值 具体值

![1585994804676](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/redis/4.png)

 

```
setex(set with expire)键秒值/setnx(set if not exist)
```

setex:设置带过期时间的key，动态设置。
setex 键 秒值 真实值

![1585994856695](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/redis/5.png)

setnx:只有在 key 不存在时设置 key 的值。

![1585994879547](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/redis/6.png)

```
mset/mget/msetnx
```

mset:同时设置一个或多个 key-value 对。

![1585994932941](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/redis/7.png)

mget:获取所有(一个或多个)给定 key 的值。

![1585994976373](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/redis/8.png)

msetnx:同时设置一个或多个 key-value 对，当且仅当所有给定 key 都不存在。

![1585995002115](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/redis/9.png)

```
getset(先get再set)
```

getset:将给定 key 的值设为 value ，并返回 key 的旧值(old value)。
简单一句话，先get然后立即set

![1585995048342](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/redis/10.png)



### Redis列表(List)

#### 常用

![1585995221265](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/redis/12.png)

![1585995238254](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/redis/13.png)

#### 案例

```
lpush/rpush/lrange
```

```
lpop/rpop
```

![1585995337319](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/redis/14.png)

```
lindex，按照索引下标获得元素(从上到下)
```

通过索引获取列表中的元素 lindex key index

![1585995391787](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/redis/15.png)

```
llen
```

```
 lrem key 删N个value
```

* 从left往right删除2个值等于v1的元素，返回的值为实际删除的数量
 * LREM list3 0 值，表示删除全部给定的值。零个就是全部值

![1585995452444](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/redis/16.png)

```
 ltrim key 开始index 结束index，截取指定范围的值后再赋值给key
```

ltrim：截取指定索引区间的元素，格式是ltrim list的key 起始索引 结束索引

![1585995502196](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/redis/17.png)

```
 rpoplpush 源列表 目的列表
```

移除列表的最后一个元素，并将该元素添加到另一个列表并返回

![1585995547020](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/redis/18.png)

```
 lset key index value
```

![1585995592111](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/redis/19.png)

```
 linsert key  before/after 值1 值2
```

在list某个已有值的前后再添加具体值

![1585995639450](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/redis/20.png)



### Redis集合(Set)

#### 常用

![1585995698562](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/redis/21.png)

#### 案例

```
sadd/smembers/sismember
```

![1585995735994](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/redis/22.png)

```
scard，获取集合里面的元素个数
```

获取集合里面的元素个数

![1585995770747](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/redis/23.png)

```
 srem key value 删除集合中元素
```

![1585995810071](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/redis/24.png)

```
 srandmember key 某个整数(随机出几个数)
```

*   从set集合里面随机取出2个
 *   如果超过最大数量就全部取出，
 *   如果写的值是负数，比如-3 ，表示需要取出3个，但是可能会有重复值。

![1585995855484](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/redis/25.png)

```
 spop key 随机出栈
```

![1585995884347](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/redis/26.png)

```
 smove key1 key2 在key1里某个值      作用是将key1里的某个值赋给key2
```

![1585995923634](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/redis/27.png)

#### 数学集合类

##### 差集：sdiff

在第一个set里面而不在后面任何一个set里面的项

![1585995977625](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/redis/28.png)

##### 交集：sinter

![1585996002650](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/redis/29.png)

##### 并集：sunion

![1585996028469](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/redis/30.png)



### Redis哈希(Hash)

#### 常用

![1585996081715](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/redis/31.png)

#### 案例

```
hset/hget/hmset/hmget/hgetall/hdel
```

![1585996133004](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/redis/32.png)

![1585996155149](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/redis/33.png)

```
hlen
```

```
 hexists key 在key里面的某个值的key
```

```
hkeys/hvals
```

![1585996199153](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/redis/34.png)

```
hincrby/hincrbyfloat
```

![1585996228252](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/redis/35.png)

```
hsetnx
```

不存在赋值，存在了无效

![1585996274616](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/redis/36.png)



### Redis有序集合Zset(sorted set)

#### 常用

![1585996334841](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/redis/37.png)

![1585996352814](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/redis/38.png)

#### 案例

```
zadd/zrange
```

![1585996398591](C:\Users\tll\AppData\Roaming\Typora\typora-user-images\1585996398591.png)

```
 zrangebyscore key 开始score 结束score
```

![1585996426257](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/redis/39.png)

![1585996444249](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/redis/40.png)

```
 zrem key 某score下对应的value值，作用是删除元素
```


删除元素，格式是zrem zset的key 项的值，项的值可以是多个zrem key score某个对应值，可以是多个值

![1585996502891](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/redis/41.png)

```
 zcard/zcount key score区间/zrank key values值，作用是获得下标值/zscore key 对应值,获得分数
```

##### zcard ：获取集合中元素个数

![1585996544615](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/redis/42.png)

##### zcount ：获取分数区间内元素个数，zcount key 开始分数区间 结束分数区间

![1585996570867](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/redis/43.png)

##### zrank： 获取value在zset中的下标位置

![1585996602676](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/redis/44.png)

##### zscore：按照值获得对应的分数

![1585996632251](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/redis/45.png)

```
 zrevrank key values值，作用是逆序获得下标值
```

正序、逆序获得下标索引值

![1585996676600](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/redis/46.png)

```
zrevrange
```

![1585996705998](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/redis/47.png)

```
 zrevrangebyscore  key 结束score 开始score
```

zrevrangebyscore zset1 90 60 withscores    分数是反着来的

![1585996748507](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/redis/48.png)





# 3.谈下你对 Redis 的了解？

Redis（全称：Remote Dictionary Server 远程字典服务）是一个开源的使用 ANSI C 语言编写、支持网络、可基于内存亦可持久化的日志型、Key-Value 数据库，并提供多种语言的 API。



# 4.Redis 一般都有哪些使用场景？

![img](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/redis/49.jpg) 

##### Redis 适合的场景

1.缓存：减轻 MySQL 的查询压力，提升系统性能；

2.排行榜：利用 Redis 的 SortSet	（有序集合）实现；
3.计算器/限速器：利用 Redis 中原子性的自增操作，我们可以统计类似用户点赞数、用	户访问数等。这类操作如果用 MySQL，频繁的读写会带来相当大的压力；限速器比较典	型的使用场景是限制某个用户访问某个 API 的频率，常用的有抢购时，防止用户疯狂	点击带来不必要的压力；
4.好友关系：利用集合的一些命令，比如求交集、并集、差集等。可以方便解决一些共同	好友、共同爱好之类的功能；

5.消息队列：除了 Redis 自身的发布/订阅模式，我们	也可以利用 List 来实现一个队列机制，比如：到货通知、邮件发送之类的需求，不需	要高可靠，但是会带来非常大的 DB 压力，完全可以用 List 来完成异步解耦；

6.Session 共享：Session 是保存在服务器的文件中，如果是集群服务，同一个用户过来	可能落在不同机器上，这就会导致用户频繁登陆；采用 Redis 保存 Session 后，无论	用户落在那台机器上都能够获取到对应的 Session 信息。

##### Redis 不适合的场景

数据量太大、数据访问频率非常低的业务都不适合使用 Redis，数据太大会增加成本，访问频率太低，保存在内存中纯属浪费资源。



# 5.Redis 为什么是单线程的？

官方 FAQ 表示，因为 Redis 是基于内存的操作，CPU 不是 Redis 的瓶颈，Redis 的瓶颈最有可能是机器内存的大小或者网络带宽。既然单线程容易实现，而且 CPU 不会成为瓶颈，那就顺理成章地采用单线程的方案了，毕竟采用多线程会有很多麻烦。



# 6.Redis 为什么这么快？

1.完全基于内存，绝大部分请求是纯粹的内存操作，非常快速；

2.数据结构简单，对数据操作也简单；

3.采用单线程，避免了不必要的上下文切换和竞争条件，也不存在多进程或者多线程	导致的切换而消耗 CPU，不用去考虑各种锁的问题，不存在加锁释放锁操作，没有	因为可能出现死锁而导致的性能消耗；
4.使用多路 I/O 复用模型，非阻塞 IO。



# 7.什么是缓存穿透？怎么解决？

缓存穿透是指查询一个一定不存在的数据，由于缓存是不命中时需要从数据库查询，查不到数据则不写入缓存，这将导致这个不存在的数据每次请求都要到数据库去查询，造成缓存穿透。

#### 解决办法

1.缓存空对象：如果一个查询返回的数据为空（不管是数据不存在，还是系统故障），我们仍然把这个空结果进行缓存，但它的过期时间会很短，最长不超过五分钟。

缓存空对象带来的问题：

```
1.空值做了缓存，意味着缓存中存了更多的键，需要更多的内存空间，比较有效的方法是针对这类数据设置一个较短的过期时间，让其自动剔除。

2.缓存和存储的数据会有一段时间窗口的不一致，可能会对业务有一定影响。例如:过期时间设置为 5分钟，如果此时存储添加了这个数据，那此段时间就会出现缓存和存储数据的不一致，此时可以利用消息系统或者其他方式清除掉缓存层中的空对象。
```

2.布隆过滤器：将所有可能存在的数据哈希到一个足够大的 bitmap 中，一个一定不存在的数据会被这个 bitmap 拦截掉，从而避免了对底层存储系统的查询压力。



# 8.什么是缓存雪崩？该如何解决？

​	如果缓存集中在一段时间内失效，发生大量的缓存穿透，所有的查询都落在数据库上，造成了缓存雪崩。

#### 解决办法

1.加锁排队：在缓存失效后，通过加锁或者队列来控制读数据库写缓存的线程数量.比如对某个 key 只允许一个线程查询数据和写缓存，其他线程等待；

2.数据预热：可以通过缓存 reload 机制，预先去更新缓存，再即将发生大并发访问前手动触发加载缓存不同的 key，设置不同的过期时间，让缓存失效的时间点尽量		均匀；

3.做二级缓存，或者双缓存策略：Cache1 为原始缓存，Cache2 为拷贝缓存，Cache1 失效时，可以访问 Cache2，Cache1 缓存失效时间设置为短期，Cache2 设置为长期。

4.在缓存的时候给过期时间加上一个随机值，这样就会大幅度的减少缓存在同一时间过期。

# 缓存穿透，缓存击穿，缓存雪崩解决方案分析

前言
设计一个缓存系统，不得不要考虑的问题就是：缓存穿透、缓存击穿与失效时的雪崩效应。

**缓存穿透**
缓存穿透是指查询一个一定不存在的数据，由于缓存是不命中时被动写的，并且出于容错考虑，如果从存储层查不到数据则不写入缓存，这将导致这个不存在的数据每次请求都要到存储层去查询，失去了缓存的意义。在流量大时，可能DB就挂掉了，要是有人利用不存在的key频繁攻击我们的应用，这就是漏洞。

**解决方案**
有很多种方法可以有效地解决缓存穿透问题，最常见的则是采用布隆过滤器，将所有可能存在的数据哈希到一个足够大的bitmap中，一个一定不存在的数据会被 这个bitmap拦截掉，从而避免了对底层存储系统的查询压力。另外也有一个更为简单粗暴的方法（我们采用的就是这种），如果一个查询返回的数据为空（不管是数 据不存在，还是系统故障），我们仍然把这个空结果进行缓存，但它的过期时间会很短，最长不超过五分钟。

**缓存雪崩**
缓存雪崩是指在我们设置缓存时采用了相同的过期时间，导致缓存在某一时刻同时失效，请求全部转发到DB，DB瞬时压力过重雪崩。

**解决方案**
缓存失效时的雪崩效应对底层系统的冲击非常可怕。大多数系统设计者考虑用加锁或者队列的方式保证缓存的单线 程（进程）写，从而避免失效时大量的并发请求落到底层存储系统上。这里分享一个简单方案就时讲缓存失效时间分散开，比如我们可以在原有的失效时间基础上增加一个随机值，比如1-5分钟随机，这样每一个缓存的过期时间的重复率就会降低，就很难引发集体失效的事件。

**缓存击穿**
对于一些设置了过期时间的key，如果这些key可能会在某些时间点被超高并发地访问，是一种非常“热点”的数据。这个时候，需要考虑一个问题：缓存被“击穿”的问题，这个和缓存雪崩的区别在于这里针对某一key缓存，前者则是很多key。

缓存在某个时间点过期的时候，恰好在这个时间点对这个Key有大量的并发请求过来，这些请求发现缓存过期一般都会从后端DB加载数据并回设到缓存，这个时候大并发的请求可能会瞬间把后端DB压垮。

**解决方案**
**1.使用互斥锁(mutex key)**
业界比较常用的做法，是使用mutex。简单地来说，就是在缓存失效的时候（判断拿出来的值为空），不是立即去load db，而是先使用缓存工具的某些带成功操作返回值的操作（比如Redis的SETNX或者Memcache的ADD）去set一个mutex key，当操作返回成功时，再进行load db的操作并回设缓存；否则，就重试整个get缓存的方法。

SETNX，是「SET if Not eXists」的缩写，也就是只有不存在的时候才设置，可以利用它来实现锁的效果。在redis2.6.1之前版本未实现setnx的过期时间，所以这里给出两种版本代码参考：

//2.6.1前单机版本锁

```java
String get(String key) {  
   String value = redis.get(key);  
   if (value  == null) {  
    if (redis.setnx(key_mutex, "1")) {  
        // 3 min timeout to avoid mutex holder crash  
        redis.expire(key_mutex, 3 * 60)  
        value = db.get(key);  
        redis.set(key, value);  
        redis.delete(key_mutex);  
    } else {  
        //其他线程休息50毫秒后重试  
        Thread.sleep(50);  
        get(key);  
    }  
  }  
}
```

最新版本代码：

```java
public String get(key) {
      String value = redis.get(key);
      if (value == null) { //代表缓存值过期
          //设置3min的超时，防止del操作失败的时候，下次缓存过期一直不能load db
		  if (redis.setnx(key_mutex, 1, 3 * 60) == 1) {  //代表设置成功
               value = db.get(key);
                      redis.set(key, value, expire_secs);
                      redis.del(key_mutex);
              } else {  //这个时候代表同时候的其他线程已经load db并回设到缓存了，这时候重试获取缓存值即可
                      sleep(50);
                      get(key);  //重试
              }
          } else {
              return value;      
          }
 }
```



memcache代码：

```java
if (memcache.get(key) == null) {  
    // 3 min timeout to avoid mutex holder crash  
    if (memcache.add(key_mutex, 3 * 60 * 1000) == true) {  
        value = db.get(key);  
        memcache.set(key, value);  
        memcache.delete(key_mutex);  
    } else {  
        sleep(50);  
        retry();  
    }  
} 
```



2. "提前"使用互斥锁(mutex key)：
在value内部设置1个超时值(timeout1), timeout1比实际的memcache timeout(timeout2)小。当从cache读取到timeout1发现它已经过期时候，马上延长timeout1并重新设置到cache。然后再从数据库加载数据并设置到cache中。伪代码如下：

```java
v = memcache.get(key);  
if (v == null) {  
    if (memcache.add(key_mutex, 3 * 60 * 1000) == true) {  
        value = db.get(key);  
        memcache.set(key, value);  
        memcache.delete(key_mutex);  
    } else {  
        sleep(50);  
        retry();  
    }  
} else {  
    if (v.timeout <= now()) {  
        if (memcache.add(key_mutex, 3 * 60 * 1000) == true) {  
            // extend the timeout for other threads  
            v.timeout += 3 * 60 * 1000;  
            memcache.set(key, v, KEY_TIMEOUT * 2);  
  
            // load the latest value from db  
            v = db.get(key);  
            v.timeout = KEY_TIMEOUT;  
            memcache.set(key, value, KEY_TIMEOUT * 2);  
            memcache.delete(key_mutex);  
        } else {  
            sleep(50);  
            retry();  
        }  
    }  
} 
```

3. "永远不过期"：  
这里的“永远不过期”包含两层意思：

(1) 从redis上看，确实没有设置过期时间，这就保证了，不会出现热点key过期问题，也就是“物理”不过期。

(2) 从功能上看，如果不过期，那不就成静态的了吗？所以我们把过期时间存在key对应的value里，如果发现要过期了，通过一个后台的异步线程进行缓存的构建，也就是“逻辑”过期

        从实战看，这种方法对于性能非常友好，唯一不足的就是构建缓存时候，其余线程(非构建缓存的线程)可能访问的是老数据，但是对于一般的互联网功能来说这个还是可以忍受。

```java
String get(final String key) {  
        V v = redis.get(key);  
        String value = v.getValue();  
        long timeout = v.getTimeout();  
        if (v.timeout <= System.currentTimeMillis()) {  
            // 异步更新后台异常执行  
            threadPool.execute(new Runnable() {  
                public void run() {  
                    String keyMutex = "mutex:" + key;  
                    if (redis.setnx(keyMutex, "1")) {  
                        // 3 min timeout to avoid mutex holder crash  
                        redis.expire(keyMutex, 3 * 60);  
                        String dbValue = db.get(key);  
                        redis.set(key, dbValue);  
                        redis.delete(keyMutex);  
                    }  
                }  
            });  
        }  
        return value;  
}
```



总结
针对业务系统，永远都是具体情况具体分析，没有最好，只有最合适。

最后，对于缓存系统常见的缓存满了和数据丢失问题，需要根据具体业务分析，通常我们采用LRU策略处理溢出，Redis的RDB和AOF持久化策略来保证一定情况下的数据安全。



# 9.怎么保证缓存和数据库数据的一致性？

如题，现在很多架构都采用了Redis+MySQL来进行存储，但是由于多方面的原因，总会导致Redis和MySQL之间出现数据的不一致性.

例如如果一个事务执行失败回滚了，但是如果采取了先写Redis的方式，就会造成Redis和MySQL数据库的不一致，再比如说，一个事务写入了MySQL，但是此时还未写入Redis，如果这时候有用户访问Redis，则此时就会出现数据不一致。

为了解决这些问题，本文将着重讨论，如何保证MySQL和Redis之间存在一个合理的数据一致性方案。

### 1.分别处理

针对某些对数据一致性要求不是特别高的情况下，可以将这些数据放入Redis，请求来了直接查询Redis，例如近期回复、历史排名这种实时性不强的业务。而针对那些强实时性的业务，例如虚拟货币、物品购买件数等等，
则直接穿透Redis至MySQL上，等到MySQL上写入成功，再同步更新到Redis上去。这样既可以起到Redis的分流大量查询请求的作用，又保证了关键数据的一致性。



### 2.高并发情况下

此时如果写入请求较多，则直接写入Redis中去，然后间隔一段时间，批量将所有的写入请求，刷新到MySQL中去； 如果此时写入请求不多，则可以在每次写入Redis，都立刻将该命令同步至MySQL中去。这两种方法有利有弊，需要根据不同的场景来权衡.



### 3.基于订阅binlog的同步机制



阿里巴巴的一款开源框架canal，提供了一种发布/ 订阅模式的同步机制，通过该框架我们可以对MySQL的binlog进行订阅，这样一旦MySQL中产生了新的写入、更新、删除等操作，就可以把binlog相关的消息推送至Redis，
Redis再根据binlog中的记录，对Redis进行更新。值得注意的是，binlog需要手动打开，并且不会记录关于MySQL查询的命令和操作。
其实这种机制，很类似MySQL的主从备份机制，因为MySQL的主备也是通过binlog来实现的数据一致性。
而canal正是模仿了slave数据库的备份请求，使得Redis的数据更新达到了相同的效果。
如下图就可以看到Slave数据库中启动了2个线程，一个是MySQL SQL线程，这个线程跟Matser数据库中起的线程是一样的，
负责MySQL的业务率执行，而另外一个线程就是MySQL的I/O线程，这个线程的主要作用就是同步Master 数据库中的binlog，达到数据备份的效果。而binlog就可以理解为一堆SQL语言组成的日志。



# 10.Redis 持久化有几种方式？

持久化就是把内存的数据写到磁盘中去，防止服务宕机了内存数据丢失。Redis 提供了两种持久化方式：RDB（默认） 和 AOF。

### RDB

RDB 是 Redis DataBase 的缩写。按照一定的时间周期策略把内存的数据以快照的形式保存到硬盘的二进制文件。即 Snapshot 快照存储，对应产生的数据文件为 dump.rdb，通过配置文件中的 save 参数来定义快照的周期。核心函数：rdbSave（生成 RDB 文件）和 rdbLoad（从文件加载内存）两个函数。![img](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/redis/50.jpg)



### AOF

AOF 是 Append-only file 的缩写。Redis会将每一个收到的写命令都通过 Write 函数追加到文件最后，类似于 MySQL 的 binlog。当 Redis 重启是会通过重新执行文件中保存的写命令来在内存中重建整个数据库的内容。每当执行服务器（定时）任务或者函数时，flushAppendOnlyFile 函数都会被调用， 这个函数执行以下两个工作：

· 

WRITE：根据条件，将 aof_buf 中的缓存写入到 AOF 文件；

SAVE：根据条件，调用 fsync 或 fdatasync 函数，将 AOF 文件保存到磁盘中。

![img](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/redis/51.jpg) 



#### RDB 和 AOF 的区别：

```
1.AOF 文件比 RDB 更新频率高，优先使用 AOF 还原数据；

2.AOF比 RDB 更安全也更大；

3.RDB 性能比 AOF 好；

4.如果两个都配了优先加载 AOF。
```



# 11.Redis 怎么实现分布式锁？

Redis 为单线程模式，采用队列模式将并发访问变成串行访问，且多客户端对 Redis 的连接并不存在竞争关系。Redis 中可以使用 SETNX 命令实现分布式锁。一般使用 setnx(set if not exists) 指令，只允许被一个程序占有，使用完调用 del 释放锁。



# 12.Redis 淘汰策略有哪些？

```
1.volatile-lru：从已设置过期时间的数据集（server. db[i]. expires）中挑选最近最少使用的数据淘汰；

2.volatile-random：从已设置过期时间的数据集（server. db[i]. expires）中任意选择数据淘汰。

3.allkeys-lru：从数据集（server. db[i]. dict）中挑选最近最少使用的数据淘汰。

4.allkeys-random：从数据集（server. db[i]. dict）中任意选择数据淘汰。

5.volatile-ttl：从已设置过期时间的数据集（server. db[i]. expires）中挑选将要过期的数据淘汰。

6.no-enviction（驱逐）：禁止驱逐数据。
```



# 13.Redis的原子性如何保证

对于Redis而言，命令的原子性指的是：一个操作不可再分，操作要么执行要么不执行，Redis之所以是原子的，是因为Redis是单线程的。

Redis所有单个命令的执行都是原子的，Redis是实现事务的原理：

1.批量操作在发送EXEC（执行）命令前被放入队列缓存；

2.收到 EXEC 命令后进入事务执行，事务中任意命令执行失败，其余的命令都不会被执行；

3.在事务执行过程，其他客户端提交的命令请求不会插入到事务执行命令序列中。



# 14.Redis 有哪些架构模式讲讲各自的特点

### 单机版

![img](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/redis/52.jpg) 

特点：简单

问题：

1、内存容量有限 2、处理能力有限 3、无法高可用。



### 主从复制

![img](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/redis/53.jpg) 

Redis 的复制（replication）功能允许用户根据一个 Redis 服务器来创建任意多个该服务器的复制品，其中被复制的服务器为主服务器（master），而通过复制创建出来的服务器复制品则为从服务器（slave）。 只要主从服务器之间的网络连接正常，主从服务器两者会具有相同的数据，主服务器就会一直将发生在自己身上的数据更新同步 给从服务器，从而一直保证主从服务器的数据相同。

特点：

1、master/slave 角色

2、master/slave 数据相同

3、降低 master 读压力在转交从库

问题：

无法保证高可用

没有解决 master 写的压力



### 哨兵

![img](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/redis/54.jpg) 

Redis sentinel 是一个分布式系统中监控 redis 主从服务器，并在主服务器下线时自动进行故障转移。其中三个特性：

监控（Monitoring）：  Sentinel 会不断地检查你的主服务器和从服务器是否运作正常。

提醒（Notification）： 当被监控的某个 Redis 服务器出现问题时， Sentinel 可以通过 API 向管理员或者其他应用程序发送通知。

自动故障迁移（Automatic failover）： 当一个主服务器不能正常工作时， Sentinel 会开始一次自动故障迁移操作。

特点：

1、保证高可用

2、监控各个节点

3、自动故障迁移

缺点：主从模式，切换需要时间丢数据

没有解决 master 写的压力



### 集群（proxy 型）

![img](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/redis/55.jpg) 

Twemproxy 是一个 Twitter 开源的一个 redis 和 memcache 快速/轻量级代理服务器； Twemproxy 是一个快速的单线程代理程序，支持 Memcached ASCII 协议和 redis 协议。

特点：1、多种 hash 算法：MD5、CRC16、CRC32、CRC32a、hsieh、murmur、Jenkins 

2、支持失败节点自动删除

3、后端 Sharding 分片逻辑对业务透明，业务方的读写方式和操作单个 Redis 一致

缺点：增加了新的 proxy，需要维护其高可用。

 

failover 逻辑需要自己实现，其本身不能支持故障的自动转移可扩展性差，进行扩缩容都需要手动干预



### 集群（直连型）

![img](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/redis/56.jpg) 

从redis 3.0之后版本支持redis-cluster集群，Redis-Cluster采用无中心结构，每个节点保存数据和整个集群状态,每个节点都和其他所有节点连接。

特点：

1、无中心架构（不存在哪个节点影响性能瓶颈），少了 proxy 层。

2、数据按照 slot 存储分布在多个节点，节点间数据共享，可动态调整数据分布。

3、可扩展性，可线性扩展到 1000 个节点，节点可动态添加或删除。

4、高可用性，部分节点不可用时，集群仍可用。通过增加 Slave 做备份数据副本

5、实现故障自动 failover，节点之间通过 gossip 协议交换状态信息，用投票机制完成 Slave到 Master 的角色提升。

缺点：

1、资源隔离性较差，容易出现相互影响的情况。

2、数据通过异步复制,不保证数据的强一致性





# 15.使用过Redis做异步队列么，你是怎么用的？有什么缺点？

一般使用list结构作为队列，rpush生产消息，lpop消费消息。当lpop没有消息的时候，要适当sleep一会再重试。

缺点：

在消费者下线的情况下，生产的消息会丢失，得使用专业的消息队列如rabbitmq等。



# 16.能不能生产一次消费多次呢？

使用pub/sub主题订阅者模式，可以实现1:N的消息队列。

进程间的一种消息通信模式：发送者(pub)发送消息，订阅者(sub)接收消息。

![img](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/redis/57.png) 

### 案例

先订阅后发布后才能收到消息，

1 可以一次性订阅多个，SUBSCRIBE c1 c2 c3

2 消息发布，PUBLISH c2 hello-redis

===============================================================================

3 订阅多个，通配符*， PSUBSCRIBE new*

4 收取消息， PUBLISH new1 redis2015

 

# 17.Redis相比memcached有哪些优势？

(1) memcached所有的值均是简单的字符串，redis作为其替代者， 支持更为丰富的数据类型
(2) redis的速度比memcached快很多
(3) redis可以持久化其数据





# 18.Redis如何存储复杂的数据？

### 复杂数据存储

一般的数据存储，就是一个key对应一个简单字符串，可以要想向mysql一样，保存或者获取某一列的所有值呢，
例如,用户表结构为

| id   | name | age  |
| ---- | ---- | ---- |
| 1    | 张三 | 19   |
| 2    | 李四 | 23   |

这种情况一般可以利用json存储或者是hash

#### json存储

```
redis 127.0.0.1:6379> set charmtest:user:1 '{"name":"张三","age":"19"}'OK

redis 127.0.0.1:6379> set charmtest:user:2 '{"name":"李四","age":"23"}'OK

redis 127.0.0.1:6379> get charmtest:user:1"{"name":"张三","age":"19"}"

redis 127.0.0.1:6379> get charmtest:user:2"{"name":"李四","age":"23"}"
```



#### hash存储

```
redis 127.0.0.1:6379:0>hset 'charmhash:user:1' name '张三'

"1"

redis 127.0.0.1:6379:0>hset 'charmhash:user:1' age 19

"1"
```



#### 获取数据

```
redis 127.0.0.1:6379:0>hget 'charmhash:user:1' name

"张三"

redis 127.0.0.1:6379:0>hget 'charmhash:user:1' age

"19"
```



# 19.Redis的过期策略

我们都知道，Redis是key-value数据库，我们可以设置Redis中缓存的key的过期时间。Redis的过期策略就是指当Redis中缓存的key过期了，Redis如何处理。

过期策略通常有以下三种

· 定时过期：每个设置过期时间的key都需要创建一个定时器，到过期时间就会立即清除。该策略可以立即清除过期的数据，对内存很友好；但是会占用大量的CPU资源去处理过期的数据，从而影响缓存的响应时间和吞吐量。

· 惰性过期：只有当访问一个key时，才会判断该key是否已过期，过期则清除。该策略可以最大化地节省CPU资源，却对内存非常不友好。极端情况可能出现大量的过期key没有再次被访问，从而不会被清除，占用大量内存。

· 定期过期：每隔一定的时间，会扫描一定数量的数据库的expires字典中一定数量的key，并清除其中已过期的key。该策略是前两者的一个折中方案。通过调整定时扫描的时间间隔和每次扫描的限定耗时，可以在不同情况下使得CPU和内存资源达到最优的平衡效果。
(expires字典会保存所有设置了过期时间的key的过期时间数据，其中，key是指向键空间中的某个键的指针，value是该键的毫秒精度的UNIX时间戳表示的过期时间。键空间是指该Redis集群中保存的所有键。)

Redis中同时使用了惰性过期和定期过期两种过期策略。







# 20.Redis底层数据结构

### 1.简单动态字符串

　　第一篇文章我们就说过 Redis 是用 C 语言写的，但是对于Redis的字符串，却不是 C 语言中的字符串（即以空字符’\0’结尾的字符数组），它是自己构建了一种名为 简单动态字符串（simple dynamic string,SDS）的抽象类型，并将 SDS 作为 Redis的默认字符串表示。

SDS 定义：

```c
struct sdshdr{

   //记录buf数组中已使用字节的数量

   //等于 SDS 保存字符串的长度

   int len;

   //记录 buf 数组中未使用字节的数量

   int free;

   //字节数组，用于保存字符串

   char buf[];

}
```

　　用SDS保存字符串 “Redis”具体图示如下：

　　 ![img](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/redis/58.jpg)

　　　　　　　　　图片来源：《Redis设计与实现》

 　我们看上面对于 SDS 数据类型的定义：

　　1、len 保存了SDS保存字符串的长度

　　2、buf[] 数组用来保存字符串的每个元素

　　3、free j记录了 buf 数组中未使用的字节数量

　　上面的定义相对于 C 语言对于字符串的定义，多出了 len 属性以及 free 属性。为什么不使用C语言字符串实现，而是使用 SDS呢？这样实现有什么好处？

##### ①、常数复杂度获取字符串长度

　　由于 len 属性的存在，我们获取 SDS 字符串的长度只需要读取 len 属性，时间复杂度为 O(1)。而对于 C 语言，获取字符串的长度通常是经过遍历计数来实现的，时间复杂度为 O(n)。通过 strlen key 命令可以获取 key 的字符串长度。

##### ②、杜绝缓冲区溢出

　　我们知道在 C 语言中使用 strcat 函数来进行两个字符串的拼接，一旦没有分配足够长度的内存空间，就会造成缓冲区溢出。而对于 SDS 数据类型，在进行字符修改的时候，会首先根据记录的 len 属性检查内存空间是否满足需求，如果不满足，会进行相应的空间扩展，然后在进行修改操作，所以不会出现缓冲区溢出。

##### ③、减少修改字符串的内存重新分配次数

　　C语言由于不记录字符串的长度，所以如果要修改字符串，必须要重新分配内存（先释放再申请），因为如果没有重新分配，字符串长度增大时会造成内存缓冲区溢出，字符串长度减小时会造成内存泄露。

　　而对于SDS，由于len属性和free属性的存在，对于修改字符串SDS实现了空间预分配和惰性空间释放两种策略：

　　1、空间预分配：对字符串进行空间扩展的时候，扩展的内存比实际需要的多，这样可以减少连续执行字符串增长操作所需的内存重分配次数。

　　2、惰性空间释放：对字符串进行缩短操作时，程序不立即使用内存重新分配来回收缩短后多余的字节，而是使用 free 属性将这些字节的数量记录下来，等待后续使用。（当然SDS也提供了相应的API，当我们有需要时，也可以手动释放这些未使用的空间。）

##### ④、二进制安全

　　因为C字符串以空字符作为字符串结束的标识，而对于一些二进制文件（如图片等），内容可能包括空字符串，因此C字符串无法正确存取；而所有 SDS 的API 都是以处理二进制的方式来处理 buf 里面的元素，并且 SDS 不是以空字符串来判断是否结束，而是以 len 属性表示的长度来判断字符串是否结束。

##### ⑤、兼容部分 C 字符串函数

　　虽然 SDS 是二进制安全的，但是一样遵从每个字符串都是以空字符串结尾的惯例，这样可以重用 C 语言库<string.h> 中的一部分函数。

##### ⑥、总结

　　![img](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/redis/59.jpg)

一般来说，SDS 除了保存数据库中的字符串值以外，SDS 还可以作为缓冲区（buffer）：包括 AOF 模块中的AOF缓冲区以及客户端状态中的输入缓冲区。后面在介绍Redis的持久化时会进行介绍。

### 2.链表

　　链表是一种常用的数据结构，C 语言内部是没有内置这种数据结构的实现，所以Redis自己构建了链表的实现。

链表定义：

　　通过多个 listNode 结构就可以组成链表，这是一个双端链表，Redis还提供了操作链表的数据结构：

```c
typedef struct listNode{
    //前置节点
    struct listNode *prev;
    //后置节点
    struct listNode *next;
    //节点的值
    void *value; 
}listNode
```

　　

```c
typedef struct list{

   //表头节点

   listNode *head;

   //表尾节点

   listNode *tail;

   //链表所包含的节点数量

   unsigned  long len;

   //节点值复制函数

   void (free) (void *ptr);

   //节点值释放函数

   void (free) (void *ptr);

   //节点值对比函数

   int (*match) (void *ptr,void *key);

}list;
```

　　![img](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/redis/60.jpg)

　　Redis链表特性：

　　①、双端：链表具有前置节点和后置节点的引用，获取这两个节点时间复杂度都为O(1)。

　　②、无环：表头节点的 prev 指针和表尾节点的 next 指针都指向 NULL,对链表的访问都是以 NULL 结束。　　

　　③、带链表长度计数器：通过 len 属性获取链表长度的时间复杂度为 O(1)。

　　④、多态：链表节点使用 void* 指针来保存节点值，可以保存各种不同类型的值。



### 3.字典

　　字典又称为符号表或者关联数组、或映射（map），是一种用于保存键值对的抽象数据结构。字典中的每一个键 key 都是唯一的，通过 key 可以对值来进行查找或修改。C 语言中没有内置这种数据结构的实现，所以字典依然是 Redis自己构建的。



　　哈希表结构定义：

```c
typedef struct dictht{

   //哈希表数组

   dictEntry **table;

   //哈希表大小

   unsigned long size;

   //哈希表大小掩码，用于计算索引值

   //总是等于 size-1

   unsigned long sizemask;

   //该哈希表已有节点的数量

   unsigned long used;

 

}dictht
```

　　哈希表是由数组 table 组成，table 中每个元素都是指向 dict.h/dictEntry 结构，dictEntry 结构定义如下：

```c
typedef struct dictEntry{

   //键

   void *key;

   //值

   union{

     void *val;

     uint64_tu64;

     int64_ts64;

   }v;

   //指向下一个哈希表节点，形成链表

   struct dictEntry *next;

}dictEntry
```

　　key 用来保存键，val 属性用来保存值，值可以是一个指针，也可以是uint64_t整数，也可以是int64_t整数。

　　注意这里还有一个指向下一个哈希表节点的指针，我们知道哈希表最大的问题是存在哈希冲突，如何解决哈希冲突，有开放地址法和链地址法。这里采用的便是链地址法，通过next这个指针可以将多个哈希值相同的键值对连接在一起，用来解决***\*哈希冲突\****。

　　![img](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/redis/61.jpg)

　　①、哈希算法：Redis计算哈希值和索引值方法如下：

```java
#1、使用字典设置的哈希函数，计算键 key 的哈希值

hash = dict->type->hashFunction(key);

#2、使用哈希表的sizemask属性和第一步得到的哈希值，计算索引值

index = hash & dict->ht[x].sizemask;
```

　②、解决哈希冲突：这个问题上面我们介绍了，方法是链地址法。通过字典里面的 *next 指针指向下一个具有相同索引值的哈希表节点。

　③、扩容和收缩：当哈希表保存的键值对太多或者太少时，就要通过 rerehash(重新散列）来对哈希表进行相应的扩展或者收缩。具体步骤：

　　　　　　1、如果执行扩展操作，会基于原哈希表创建一个大小等于 ht[0].used*2n 的哈希表（也就是每次扩展都是根据原哈希表已使用的空间扩大一倍创建另一个哈希表）。相反如果执行的是收缩操作，每次收缩是根据已使用空间缩小一倍创建一个新的哈希表。

　　　　　　2、重新利用上面的哈希算法，计算索引值，然后将键值对放到新的哈希表位置上。

　　　　　　3、所有键值对都迁徙完毕后，释放原哈希表的内存空间。

　④、触发扩容的条件：

　　　　　　1、服务器目前没有执行 BGSAVE 命令或者 BGREWRITEAOF 命令，并且负载因子大于等于1。

　　　　　　2、服务器目前正在执行 BGSAVE 命令或者 BGREWRITEAOF 命令，并且负载因子大于等于5。

　　　　ps：负载因子 = 哈希表已保存节点数量 / 哈希表大小。

⑤、渐近式 rehash

　　　　什么叫渐进式 rehash？也就是说扩容和收缩操作不是一次性、集中式完成的，而是分多次、渐进式完成的。如果保存在Redis中的键值对只有几个几十个，那么 rehash 操作可以瞬间完成，但是如果键值对有几百万，几千万甚至几亿，那么要一次性的进行 rehash，势必会造成Redis一段时间内不能进行别的操作。所以Redis采用渐进式 rehash,这样在进行渐进式rehash期间，字典的删除查找更新等操作可能会在两个哈希表上进行，第一个哈希表没有找到，就会去第二个哈希表上进行查找。但是进行 增加操作，一定是在新的哈希表上进行的。



### 4.跳跃表

　　跳跃表（skiplist）是一种有序数据结构，它通过在每个节点中维持多个指向其它节点的指针，从而达到快速访问节点的目的。具有如下性质：

　　1、由很多层结构组成；

　　2、每一层都是一个有序的链表，排列顺序为由高层到底层，都至少包含两个链表节点，分别是前面的head节点和后面的nil节点；

　　3、最底层的链表包含了所有的元素；

　　4、如果一个元素出现在某一层的链表中，那么在该层之下的链表也全都会出现（上一层的元素是当前层的元素的子集）；

　　5、链表中的每个节点都包含两个指针，一个指向同一层的下一个链表节点，另一个指向下一层的同一个链表节点；

　　![img](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/redis/62.jpg)

　　Redis中跳跃表节点定义如下：

```c
typedef struct zskiplistNode {

   //层

   struct zskiplistLevel{

     //前进指针

      struct zskiplistNode *forward;

      //跨度

      unsigned ***\*int\**** span;

   }level[];

 

   //后退指针

   struct zskiplistNode *backward;

   //分值

   double score;

   //成员对象

   robj *obj;

 

} zskiplistNode
```

　　多个跳跃表节点构成一个跳跃表：

```c
typedef struct zskiplist{

   //表头节点和表尾节点

   structz skiplistNode *header, *tail;

   //表中节点的数量

   unsigned long length;

   //表中层数最大的节点的层数

   int level;

 

}zskiplist;
```

　　![img](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/redis/63.jpg)

　　①、搜索：从最高层的链表节点开始，如果比当前节点要大和比当前层的下一个节点要小，那么则往下找，也就是和当前层的下一层的节点的下一个节点进行比较，以此类推，一直找到最底层的最后一个节点，如果找到则返回，反之则返回空。

　　②、插入：首先确定插入的层数，有一种方法是假设抛一枚硬币，如果是正面就累加，直到遇见反面为止，最后记录正面的次数作为插入的层数。当确定插入的层数k后，则需要将新元素插入到从底层到k层。

③、删除：在各个层中找到

包含指定值的节点，然后将节点从链表中删除即可，如果删除以后只剩下头尾两个节点，则删除这一层。

### 5.整数集合

　　整数集合（intset）是Redis用于保存整数值的集合抽象数据类型，它可以保存类型为int16_t、int32_t 或者int64_t 的整数值，并且保证集合中不会出现重复元素。

```c
typedef struct intset{

   //编码方式

   uint32_t encoding;

   //集合包含的元素数量

   uint32_t length;

   //保存元素的数组

   int8_t contents[];

 

}intset;
```

　　定义如下：

　　整数集合的每个元素都是 contents 数组的一个数据项，它们按照从小到大的顺序排列，并且不包含任何重复项。

　　length 属性记录了 contents 数组的大小。

　　需要注意的是虽然 contents 数组声明为 int8_t 类型，但是实际上contents 数组并不保存任何 int8_t 类型的值，其真正类型有 encoding 来决定。

　①、升级

　　当我们新增的元素类型比原集合元素类型的长度要大时，需要对整数集合进行升级，才能将新元素放入整数集合中。具体步骤：

　　1、根据新元素类型，扩展整数集合底层数组的大小，并为新元素分配空间。

　　2、将底层数组现有的所有元素都转成与新元素相同类型的元素，并将转换后的元素放到正确的位置，放置过程中，维持整个元素顺序都是有序的。

　　3、将新元素添加到整数集合中（保证有序）。

　　升级能极大地节省内存。

②、降级

　　整数集合不支持降级操作，一旦对数组进行了升级，编码就会一直保持升级后的状态。

### 6.压缩列表

　　压缩列表（ziplist）是Redis为了节省内存而开发的，是由一系列特殊编码的连续内存块组成的顺序型数据结构，一个压缩列表可以包含任意多个节点（entry），每个节点可以保存一个字节数组或者一个整数值。

压缩列表的原理：压缩列表并不是对数据利用某种算法进行压缩，而是将数据按照一定规则编码在一块连续的内存区域，目的是节省内存。

　　![img](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/redis/64.jpg)

　　压缩列表的每个节点构成如下：

　　![img](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/redis/65.jpg)

　　①、previous_entry_ength：记录压缩列表前一个字节的长度。previous_entry_ength的长度可能是1个字节或者是5个字节，如果上一个节点的长度小于254，则该节点只需要一个字节就可以表示前一个节点的长度了，如果前一个节点的长度大于等于254，则previous length的第一个字节为254，后面用四个字节表示当前节点前一个节点的长度。利用此原理即当前节点位置减去上一个节点的长度即得到上一个节点的起始位置，压缩列表可以从尾部向头部遍历。这么做很有效地减少了内存的浪费。

　　②、encoding：节点的encoding保存的是节点的content的内容类型以及长度，encoding类型一共有两种，一种字节数组一种是整数，encoding区域长度为1字节、2字节或者5字节长。

　　③、content：content区域用于保存节点的内容，节点内容类型和长度由encoding决定。

### 7.总结

　　大多数情况下，Redis使用简单字符串SDS作为字符串的表示，相对于C语言字符串，SDS具有常数复杂度获取字符串长度，杜绝了缓存区的溢出，减少了修改字符串长度时所需的内存重分配次数，以及二进制安全能存储各种类型的文件，并且还兼容部分C函数。

　　通过为链表设置不同类型的特定函数，Redis链表可以保存各种不同类型的值，除了用作列表键，还在发布与订阅、慢查询、监视器等方面发挥作用（后面会介绍）。

　　Redis的字典底层使用哈希表实现，每个字典通常有两个哈希表，一个平时使用，另一个用于rehash时使用，使用链地址法解决哈希冲突。

　　跳跃表通常是有序集合的底层实现之一，表中的节点按照分值大小进行排序。

　　整数集合是集合键的底层实现之一，底层由数组构成，升级特性能尽可能的节省内存。

　　压缩列表是Redis为节省内存而开发的顺序型数据结构，通常作为列表键和哈希键的底层实现之一。
