  * [一、MySQL的架构介绍](#一mysql的架构介绍)
    * [1.MySql简介](#1mysql简介)
    * [2.MySQL逻辑架构介绍](#2mysql逻辑架构介绍)
        * [1.Connectors](#1connectors)
        * [2 Management Serveices & Utilities：](#2-management-serveices--utilities：)
        * [3 Connection Pool: 连接池](#3-connection-pool-连接池)
        * [4 SQL Interface: SQL接口。](#4-sql-interface-sql接口。)
        * [5 Parser: 解析器。](#5-parser-解析器。)
        * [6 Optimizer: 查询优化器。](#6-optimizer-查询优化器。)
        * [7 Cache和Buffer： 查询缓存。](#7-cache和buffer：-查询缓存。)
        * [8 、存储引擎接口](#8-、存储引擎接口)
    * [3.MySQL存储引擎](#3mysql存储引擎)
      * [查看命令：](#查看命令：)
      * [MyISAM和InnoDB区别：](#myisam和innodb区别：)
  * [二、索引优化分析](#二索引优化分析)
    * [1.索引简介](#1索引简介)
        * [是什么？](#是什么？)
        * [优势：](#优势：)
        * [劣势：](#劣势：)
        * [mysql索引分类](#mysql索引分类)
        * [基本语法：](#基本语法：)
        * [使用Alter命令：](#使用alter命令：)
        * [mysql索引结构：](#mysql索引结构：)
        * [BTree：](#btree：)
        * [B+Tree](#btree)
        * [B树索引：](#b树索引：)
        * [B+树索引：](#b树索引：-1)
        * [其中MyISAM和InnoDB存储引擎都采用B+树索引，但底层实现方式不同](#其中myisam和innodb存储引擎都采用b树索引，但底层实现方式不同)
        * [Hash索引：](#hash索引：)
        * [哪些情况需要创建索引](#哪些情况需要创建索引)
        * [哪些情况不要创建索引](#哪些情况不要创建索引)
    * [2.性能分析](#2性能分析)
        * [MySQL Query Optimizer](#mysql-query-optimizer)
        * [MySQL常见瓶颈:](#mysql常见瓶颈)
      * [Explain](#explain)
        * [是什么（查看执行计划）](#是什么（查看执行计划）)
        * [怎么玩](#怎么玩)
        * [执行计划包含的信息](#执行计划包含的信息)
        * [各个字段解释](#各个字段解释)
        * [id](#id)
        * [select_type](#select_type)
        * [table](#table)
        * [type](#type)
        * [possible_keys](#possible_keys)
        * [key](#key)
        * [key_len](#key_len)
        * [ref](#ref)
        * [rows](#rows)
        * [Extra](#extra)
    * [3.索引优化](#3索引优化)
        * [索引失效（应该避免）](#索引失效（应该避免）)
      * [一般性建议](#一般性建议)
  * [三、查询截取分析](#三查询截取分析)
    * [查询优化](#查询优化)
        * [order by关键字优化](#order-by关键字优化)
        * [优化策略](#优化策略)
        * [小总结:](#小总结)
        * [GROUP BY关键字优化](#group-by关键字优化)
    * [慢查询日志](#慢查询日志)
        * [是什么](#是什么)
        * [日志分析工具mysqldumpslow](#日志分析工具mysqldumpslow)
    * [批量数据脚本](#批量数据脚本)
        * [往表里插入1000W数据](#往表里插入1000w数据)
    * [Show profiles](#show-profiles)
        * [是什么：](#是什么：)
        * [分析步骤:](#分析步骤)
    * [全局查询日志](#全局查询日志)
        * [永远不要在生产环境开启这个功能。](#永远不要在生产环境开启这个功能。)
  * [四、MySQL锁机制](#四mysql锁机制)
    * [概述](#概述)
      * [锁的分类](#锁的分类)
        * [从数据操作的类型（读、写）分：](#从数据操作的类型（读、写）分：)
        * [从对数据操作的颗粒度：](#从对数据操作的颗粒度：)
    * [表锁（偏读）](#表锁（偏读）)
      * [特点](#特点)
      * [案例分析](#案例分析)
        * [建表SQL](#建表sql)
        * [加读锁](#加读锁)
        * [加写锁](#加写锁)
      * [案例结论](#案例结论)
      * [表锁分析](#表锁分析)
    * [行锁（偏写）](#行锁（偏写）)
      * [特点](#特点-1)
      * [由于行锁支持事务，复习老知识](#由于行锁支持事务，复习老知识)
        * [事务（Transation）及其ACID属性](#事务（transation）及其acid属性)
        * [并发事务处理带来的问题](#并发事务处理带来的问题)
        * [事务隔离级别](#事务隔离级别)
      * [案例分析](#案例分析-1)
        * [建表SQL](#建表sql-1)
        * [行锁定基本演示](#行锁定基本演示)
        * [无索引行锁升级为表锁](#无索引行锁升级为表锁)
        * [间隙锁危害](#间隙锁危害)
        * [面试题：常考如何锁定一行](#面试题：常考如何锁定一行)
      * [案例结论](#案例结论-1)
      * [行锁分析](#行锁分析)
      * [优化建议](#优化建议)
  * [五、主从复制](#五主从复制)
    * [复制的基本原理](#复制的基本原理)
        * [三大步骤：](#三大步骤：)
    * [复制的基本原则](#复制的基本原则)
    * [复制最大问题](#复制最大问题)
        * [延时](#延时)
  * [六、参考资料](#六参考资料)  
        * [尚硅谷讲堂](#尚硅谷讲堂)  
        * [图灵学院](#图灵学院)






## 一、MySQL的架构介绍

### 1.MySql简介

​		概述：![](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/MySQL/1.png)

### 2.MySQL逻辑架构介绍

​		![2](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/MySQL/2.png)



​	![2](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/MySQL/3.png)

##### 1.Connectors

指的是不同语言中与SQL的交互

##### 2 Management Serveices & Utilities：

系统管理和控制工具

##### 3 Connection Pool: 连接池

管理缓冲用户连接，线程处理等需要缓存的需求。
负责监听对 MySQL Server 的各种请求，接收连接请求，转发所有连接请求到线程管理模块。每一个连接上 MySQL Server 的客户端请求都会被分配（或创建）一个连接线程为其单独服务。而连接线程的主要工作就是负责 MySQL Server 与客户端的通信，
接受客户端的命令请求，传递 Server 端的结果信息等。线程管理模块则负责管理维护这些连接线程。包括线程的创建，线程的 cache 等。

##### 4 SQL Interface: SQL接口。

接受用户的SQL命令，并且返回用户需要查询的结果。比如select from就是调用SQL Interface

##### 5 Parser: 解析器。

SQL命令传递到解析器的时候会被解析器验证和解析。解析器是由Lex和YACC实现的，是一个很长的脚本。
在 MySQL中我们习惯将所有 Client 端发送给 Server 端的命令都称为 query ，在 MySQL Server 里面，连接线程接收到客户端的一个 Query 后，会直接将该 query 传递给专门负责将各种 Query 进行分类然后转发给各个对应的处理模块。
主要功能：
a . 将SQL语句进行语义和语法的分析，分解成数据结构，然后按照不同的操作类型进行分类，然后做出针对性的转发到后续步骤，以后SQL语句的传递和处理就是基于这个结构的。
b.  如果在分解构成中遇到错误，那么就说明这个sql语句是不合理的

##### 6 Optimizer: 查询优化器。

SQL语句在查询之前会使用查询优化器对查询进行优化。就是优化客户端请求的 query（sql语句） ，根据客户端请求的 query 语句，和数据库中的一些统计信息，在一系列算法的基础上进行分析，得出一个最优的策略，告诉后面的程序如何取得这个 query 语句的结果
他使用的是“选取-投影-联接”策略进行查询。
       用一个例子就可以理解： select uid,name from user where gender = 1;
       这个select 查询先根据where 语句进行选取，而不是先将表全部查询出来以后再进行gender过滤
       这个select查询先根据uid和name进行属性投影，而不是将属性全部取出以后再进行过滤
       将这两个查询条件联接起来生成最终查询结果

##### 7 Cache和Buffer： 查询缓存。

他的主要功能是将客户端提交 给MySQL 的 Select 类 query 请求的返回结果集 cache 到内存中，与该 query 的一个 hash 值 做一个对应。该 Query 所取数据的基表发生任何数据的变化之后， MySQL 会自动使该 query 的Cache 失效。在读写比例非常高的应用系统中， Query Cache 对性能的提高是非常显著的。当然它对内存的消耗也是非常大的。
如果查询缓存有命中的查询结果，查询语句就可以直接去查询缓存中取数据。这个缓存机制是由一系列小缓存组成的。比如表缓存，记录缓存，key缓存，权限缓存等

##### 8 、存储引擎接口

存储引擎接口模块可以说是 MySQL 数据库中最有特色的一点了。目前各种数据库产品中，基本上只有 MySQL 可以实现其底层数据存储引擎的插件式管理。这个模块实际上只是 一个抽象类，但正是因为它成功地将各种数据处理高度抽象化，才成就了今天 MySQL 可插拔存储引擎的特色。
     从图2还可以看出，MySQL区别于其他数据库的最重要的特点就是其插件式的表存储引擎。MySQL插件式的存储引擎架构提供了一系列标准的管理和服务支持，这些标准与存储引擎本身无关，可能是每个数据库系统本身都必需的，如SQL分析器和优化器等，而存储引擎是底层物理结构的实现，每个存储引擎开发者都可以按照自己的意愿来进行开发。
    注意：存储引擎是基于表的，而不是数据库。

### 3.MySQL存储引擎

#### 查看命令：

​		![2](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/MySQL/4.png)

#### MyISAM和InnoDB区别：

​	![2](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/MySQL/5.png)







## 二、索引优化分析

### 1.索引简介

##### 是什么？

MySQL官方对索引的定义为：索引(Index)是帮助MySQL高校获取数据的数据结构。可以得到索引的本质：索引是数据结构。可以简单理解为"排好序的快速查找数据结构"。

结论：数据本身之外,数据库还维护着一个满足特定查找算法的数据结构，这些数据结构以某种方式指向数据，
这样就可以在这些数据结构的基础上实现高级查找算法,这种数据结构就是索引。

一般来说索引本身也很大，不可能全部存储在内存中，因此索引往往以文件形式存储在硬盘上。

##### 优势：

通过索引列对数据进行排序，降低数据排序成本，降低了CPU的消耗

##### 劣势：

1.实际上索引也是一张表，该表保存了主键和索引字段，并指向实体表的记录,所以索引列也是要占用空间的。

2.虽然索引大大提高了查询速度，同时却会降低更新表的速度,如果对表INSERT,UPDATE和DELETE。因为更新表时，MySQL不仅要不存数据，还要保存一下索引文件每次更新添加了索引列的字段，都会调整因为更新所带来的键值变化后的索引信息。

3.索引只是提高效率的一个因素，如果你的MySQL有大数据量的表，就需要花时间研究建立优秀的索引，或优化查询语句。

##### mysql索引分类

```
单值索引：即一个索引只包含单个列，一个表可以有多个单列索引

唯一索引：索引列的值必须唯一，但允许有空值

复合索引：即一个索引包含多个列
```

##### 基本语法：

```
创建：
1.CREATE [UNIQUE] INDEX  indexName ON mytable(columnname(length));
	//如果是CHAR,VARCHAR类型，length可以小于字段实际长度；如果是BLOB和TEXT类型，必须指定length。
2.ALTER mytable ADD [UNIQUE]  INDEX [indexName] ON(columnname(length));

删除：
DROP INDEX [indexName] ON mytable;

查看：
SHOW INDEX FROM table_name\G
```

##### 使用Alter命令：

![2](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/MySQL/6.png)

##### mysql索引结构：

##### BTree：

B树（Balance Tree）是一种多路平衡查找树，他的**每一个节点最多包含M个孩子，M就是B树的阶。**M的大小取决于磁盘页的大小。

**B-树就是B树，中间的横线不是减号，所以不要读成B减树。**

![2](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/MySQL/7.jpg)



##### B+Tree

一个m阶的B+树具有如下几个特征：

```
1.有k个子树的中间节点包含有k个元素（B树中是k-1个元素），每个元素不保存数据，只用来索引，所有数据都保存在叶子节点。

2.所有的叶子结点中包含了全部元素的信息，及指向含这些元素记录的指针，且叶子结点本身依关键字的大小自小而大顺序链接。

3.每个父节点的元素都同时存在于子节点中，是子节点中的最大（或最小）元素。

4.根节点的最大元素是整个B+树的最大元素。

5.由于父节点的元素都包含在子节点，因此所有叶子节点包括了全部的元素信息。

6.每个叶子节点都带有指向下一个节点的指针，形成一个有序链表。
```

![2](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/MySQL/8.png)



##### B树索引：

![2](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/MySQL/9.jpg)

##### B+树索引：

![2](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/MySQL/10.jpg)



##### 其中MyISAM和InnoDB存储引擎都采用B+树索引，但底层实现方式不同

InnoDB实现方式：其中叶子节点存储数据和键值即是原表中数据（聚集索引）

![2](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/MySQL/11.jpg)

MyISAM实现方式：其中叶子节点存储键值和数据表中对应数据的物理地址（非聚集索引）

![2](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/MySQL/12.jpg)

##### Hash索引：

```
1.只能使用=或<=>操作符的等式比较

2.优化器不能使用hash索引来加速order by操作

3.mysql不能确定在两个值之间大约有多少行。如果将一个myisam表改为hash索引的memory表，会影响一些查询的执行效率。

4.只能使用整个关键字来搜索一行
```

 hash index是基于哈希表实现的，只有精确匹配索引所有列的查询才会生效。对于每一行数据，存储引擎都会对所有的索引列计算一个hash code,并将的有的hash code存储在索引中，同时在哈希表中保存指向每个数据行的指针。

![2](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/MySQL/13.jpg)



##### 哪些情况需要创建索引

```
1.主键自动建立唯一索引

2.频繁作为查询的条件的字段应该创建索引

3.查询中与其他表关联的字段，外键关系建立索引

4.频繁更新的字段不适合创建索引

5.Where条件里用不到的字段不创建索引

6.单间/组合索引的选择问题，who？（在高并发下倾向创建组合索引）

7.查询中排序的字段，排序字段若通过索引去访问将大大提高排序的速度

8.查询中统计或者分组字段
```

##### 哪些情况不要创建索引

```
1.表记录太少

2.经常增删改的表

3.数据重复且分布平均的表字段，因此应该只为经常查询和经常排序的数据列建立索引。注意，如果某个数据列包含许多重复的内容，为它建立索引就没有太大的实际效果。
```



### 2.性能分析

##### MySQL Query Optimizer

![2](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/MySQL/14.png)



##### MySQL常见瓶颈:

```
CPU:CPU在饱和的时候一般发生在数据装入在内存或从磁盘上读取数据时候

IO:磁盘I/O瓶颈发生在装入数据远大于内存容量时

服务器硬件的性能瓶颈：top,free,iostat和vmstat来查看系统的性能状态
```



#### Explain

##### 是什么（查看执行计划）

```
使用EXPLAIN关键字可以模拟优化器执行SQL语句，从而知道MySQL是如何处理你的SQL语句的。分析你的查询语句或是结构的性能瓶颈
```

##### 怎么玩

```
Explain+SQL语句
```

##### 执行计划包含的信息

![2](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/MySQL/15.png)

##### 各个字段解释

##### id

select查询的序列号，包含一组数字，表示查询中执行select子句或操作表的顺序
三种情况
id相同，执行顺序由上至下

![2](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/MySQL/16.png)

id不同，如果是子查询，id的序号会递增，id值越大优先级越高，越先被执行

![2](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/MySQL/17.png)

id相同不同，同时存在

![2](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/MySQL/18.png)



##### select_type

有哪些：

![2](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/MySQL/19.png)



查询的类型，主要用于区别普通查询、联合查询、子查询等的复杂查询

```
1.SIMPLE：简单的select查询，查询中不包含子查询或者UNION

2.PRIMARY：查询中若包含任何复杂的子部分，最外层查询则被标记为

3.SUBQUERY：在SELECT或者WHERE列表中包含了子查询

4.DERIVED：在FROM列表中包含的子查询被标记为DERIVED（衍生）MySQL会递归执行这些子查询，把结果放						在临时表里。

5.UNION：若第二个SELECT出现在UNION之后，则被标记为UNION;若UNION包含在FROM子句的子查询中，外					层SELECT将被标记为：DERIVED

6.UNION RESULT：从UNION表获取结果的SELECT
```



##### table

显示这一行的数据是关于哪张表的

##### type

显示查询使用了何种类型
从最好到最差依次是：

```
system>const>eq_ref>ref>range>index>ALL
```

```
system：表只有一行记录（等于系统表），这是const类型的特例，平时不会出现，这个也可以忽略不计

const：表示通过索引一次就找到了，const用于比较primary key或者unique索引。因为只匹配一行数据，所以很			快。如将主键至于where列表中，MySQL就能将该查询转换为一个常量

eq_ref：唯一性索引，对于每个索引键，表中只有一条记录与之匹配，常见于主键或唯一索引扫描

ref：非唯一索引扫描，返回匹配某个单独值的所有行。本质上也是一种索引访问，它返回所有匹配某个单独值的		行，然而，它可能会找到多个符合条件的行，所以他应该属于查找和扫描的混合体

range：只检索给定范围的行，使用一个索引来选择行。key列显示使用了哪个索引一般就是在你的where语句中				出现了between、<、>、in等的查询这种范围扫描索引扫描比全表扫描要好，因为他只需要开始索引的				某一点，而结束语另一点，不用扫描全部索引

index：Full Index Scan,index与ALL区别为index类型只遍历索引树。这通常比ALL快，因为索引文件通常比数据		文件小。（也就是说虽然all和index都是读全表，但index是从索引中读取的，而all是从硬盘中读的）

all：FullTable Scan,将遍历全表以找到匹配的行
```

##### possible_keys

显示可能应用在这张表中的索引,一个或多个。查询涉及的字段上若存在索引，则该索引将被列出，但不一定被查询实际使用

##### key

实际使用的索引。如果为null则没有使用索引

##### key_len

表示索引中使用的字节数，可通过该列计算查询中使用的索引的长度。在不损失精确性的情况下，长度越短越好key_len显示的值为索引最大可能长度，并非实际使用长度，即key_len是根据表定义计算而得，不是通过表内检索出的

##### ref

显示索引那一列被使用了，如果可能的话，是一个常数。那些列或常量被用于查找索引列上的值

##### rows

根据表统计信息及索引选用情况，大致估算出找到所需的记录所需要读取的行数

##### Extra

包含不适合在其他列中显示但十分重要的额外信息

```
1.Using filesort：说明mysql会对数据使用一个外部的索引排序，而不是按照表内的索引顺序进行读取。
MySQL中无法利用索引完成排序操作成为“文件排序”

2.Using temporary：使用了临时表保存中间结果，MySQL在对查询结果排序时使用临时表。常见于排序order by 和分组查询 group by

3.USING index：表示相应的select操作中使用了覆盖索引（Coveing Index）,避免访问了表的数据行，效率不错！
如果同时出现using where，表明索引被用来执行索引键值的查找；
如果没有同时出现using where，表面索引用来读取数据而非执行查找动作。
```

覆盖索引（Covering Index）：

![2](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/MySQL/20.png)

```
4.Using where：表面使用了where过滤

5.using join buffer：使用了连接缓存

6.impossible where：where子句的值总是false，不能用来获取任何元组

7.select tables optimized away：在没有GROUPBY子句的情况下，基于索引优化MIN/MAX操作或者
对于MyISAM存储引擎优化COUNT(*)操作，不必等到执行阶段再进行计算，
查询执行计划生成的阶段即完成优化。

8.distinct：优化distinct，在找到第一匹配的元组后即停止找同样值的工作
```



### 3.索引优化

##### 索引失效（应该避免）

1.全值匹配我最爱

![2](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/MySQL/21.png)

2.最佳左前缀法则

![2](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/MySQL/22.png)

3.不在索引列上做任何操作（计算、函数、（自动or手动）类型转换），会导致索引失效而转向全表扫描

![2](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/MySQL/23.png)

4.存储引擎不能使用索引中范围条件右边的列

![2](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/MySQL/24.png)

5.尽量使用覆盖索引（只访问索引的查询（索引列和查询列一致）），减少select*

![2](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/MySQL/26.png)

![2](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/MySQL/27.png)

6.mysql在使用不等于（！=或者<>）的时候无法使用索引会导致全表扫描

![2](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/MySQL/28.png)

7.is null,is not null 也无法使用索引

![2](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/MySQL/29.png)

8.like以通配符开头（'$abc...'）mysql索引失效会变成全表扫描操作

![2](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/MySQL/30.png)

9.字符串不加单引号索引失效

![2](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/MySQL/31.png)

10.少用or,用它连接时会索引失效

![2](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/MySQL/32.png)

11.小总结

![2](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/MySQL/33.png)

![2](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/MySQL/34.png)



#### 一般性建议

```
对于单键索引，尽量选择针对当前query过滤性更好的索引

在选择组合索引的时候，当前Query中过滤性最好的字段在索引字段顺序中，位置越靠前越好。

在选择组合索引的时候，尽量选择可以能包含当前query中的where子句中更多字段的索引

尽可能通过分析统计信息和调整query的写法来达到选择合适索引的目的
```







## 三、查询截取分析



### 查询优化

永远小表驱动大表，类似嵌套循环Nested Loop

![2](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/MySQL/35.png)

![2](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/MySQL/36.png)

![2](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/MySQL/37.png)



##### order by关键字优化

ORDER BY子句，尽量使用Index方式排序，避免使用FileSort方式排序

尽可能在索引列上完成排序操作，遵照索引建的最佳左前缀

如果不在索引列上，filesort有两种算法：
mysql就要启动双路排序和单路排序

```
双路排序
1.MySQL4.1之前是使用双路排序，字面意思是两次扫描磁盘，最终得到数据。
读取行指针和orderby列，对他们进行排序，然后扫描已经排序好的列表，按照列表中的值重新从列表中读取对应的数据传输
2.从磁盘取排序字段，在buffer进行排序，再从磁盘取其他字段。
取一批数据，要对磁盘进行两次扫描，众所周知，I\O是很耗时的，所以在mysql4.1之后，出现了第二张改进的算法，就是单路排序。

单路排序
从磁盘读取查询需要的所有列，按照orderby列在buffer对它们进行排序，然后扫描排序后的列表进行输出，
它的效率更快一些，避免了第二次读取数据，并且把随机IO变成顺序IO，但是它会使用更多的空间，
因为它把每一行都保存在内存中了。

结论及引申出的问题
由于单路是后出来的，总体而言好过双路
但是用单路有问题
```

![2](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/MySQL/38.png)



##### 优化策略

增大sort_buffer_size参数的设置

增大max_length_for_sort_data参数的设置

![2](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/MySQL/39.png)



##### 小总结:

![2](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/MySQL/40.png)

##### GROUP BY关键字优化

```
groupby实质是先排序后进行分组，遵照索引建的最佳左前缀

当无法使用索引列，增大max_length_for_sort_data参数的设置+增大sort_buffer_size参数的设置

where高于having,能写在where限定的条件就不要去having限定了。
```



### 慢查询日志

##### 是什么

![2](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/MySQL/41.png)

##### 日志分析工具mysqldumpslow

查看mysqldumpshow的帮助信息

![2](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/MySQL/42.png)





### 批量数据脚本

##### 往表里插入1000W数据

1.建表

2.设置参数log_trust_function_createors

![2](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/MySQL/43.png)

3创建函数保证每条数据都不同

![2](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/MySQL/44.png)

​	随机产生字符串

​	随机产生部门编号

4.创建存储过程

![2](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/MySQL/45.png)

​	创建往emp表中插入数据的存储过程

​	创建往dept表中插入数据的存储过程

5.调用存储过程



### Show profiles

##### 是什么：

```
是mysql提供可以用来分析当前会话中语句执行的资源消耗情况。可以用于SQL的调优测量
```

默认情况下，参数处于关闭状态，并保存最近15次的运行结果

##### 分析步骤:

1.是否支持，看看当前的SQL版本是否支持

![2](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/MySQL/46.png)

2.开启功能，默认是关闭，使用前需要开启

3.运行SQL

```
select * from emp group by id%10 limit 150000
```

4.查看结果，show profiles;

5.诊断SQL，show profile cpu,block io for query 上一步前面的问题SQL 数字号码；

![2](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/MySQL/47.png)

![2](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/MySQL/48.png)

6.日常开发需要注意的结论

```
converting HEAP to MyISAM 查询结果太大，内存都不够用了往磁盘上搬了。

Creating tmp table 创建临时表

Copying to tmp table on disk 把内存中临时表复制到磁盘，危险！！！

locked
```







### 全局查询日志

配置启用

![2](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/MySQL/49.png)

编码启用

![2](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/MySQL/50.png)

##### 永远不要在生产环境开启这个功能。





## 四、MySQL锁机制

### 概述

#### 锁的分类

##### 从数据操作的类型（读、写）分：

```
读锁（共享锁）：针对同一份数据，多个读操作可以同时进行而不会互相影响

写锁（排它锁）：当前写操作没有完成前，它会阻断其他写锁和读锁。
```

##### 从对数据操作的颗粒度：

```
表锁

行锁
```



### 表锁（偏读）

#### 特点

```
偏向MyISAM存储引擎，开销小，加锁快，无死锁，锁定粒度大，发生锁冲突的概率最高，并发最低
```

#### 案例分析

##### 建表SQL

![2](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/MySQL/51.png)

##### 加读锁

![2](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/MySQL/52.png)

![2](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/MySQL/53.png)

![2](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/MySQL/54.png)

##### 加写锁

![2](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/MySQL/55.png)

![2](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/MySQL/56.png)

#### 案例结论

![2](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/MySQL/57.png)

#### 表锁分析

![2](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/MySQL/58.png)





### 行锁（偏写）

#### 特点

```
偏向InnoDB存储引擎，开销大，加锁慢；会出现死锁；锁定粒度最小，发生锁冲突的概率最低，并发度也最高。

InnoDB与MyISAM的最大不同有两点：一是支持事务（TRANSACTION）;二是采用了行级锁
```



#### 由于行锁支持事务，复习老知识

##### 事务（Transation）及其ACID属性

##### 并发事务处理带来的问题

```
脏读:（Dirty Reads）：事务A读到了事务B已修改但尚未提交的数据，还在这个数据基础上做了操作，此时，如果						B事务回滚，A读取的数据无效，不符合一致性要求。
不可重复读（Non-Repeatable Reads）：事务A读取到事务B已经提交的数据,不符合隔离性
幻读（Phantom Reads）:事务A读取到事务B新增的数据

脏读和幻读的区别：脏读是事务B修改了数据，幻读是事务B新增了数据.
```



##### 事务隔离级别

![2](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/MySQL/59.png)



#### 案例分析

##### 建表SQL

![2](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/MySQL/60.png)

##### 行锁定基本演示

![2](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/MySQL/61.png)

##### 无索引行锁升级为表锁

```
varchar  不用 ' '  导致系统自动转换类型, 行锁变表锁
```

##### 间隙锁危害

![2](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/MySQL/62.png)

##### 面试题：常考如何锁定一行

![2](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/MySQL/63.png)

#### 案例结论

![2](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/MySQL/64.png)

#### 行锁分析

![2](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/MySQL/65.png)

#### 优化建议

```
尽可能让所有数据检索都通过索引来完成，避免无索引行锁升级为表锁
合理设计索引，尽量缩小锁的范围
尽可能较少检索条件，避免间隙锁
尽量控制事务大小，减少锁定资源量和时间长度
尽可能低级别事务隔离
```





## 五、主从复制

### 复制的基本原理

```
slave会从master读取binlog来进行数据同步
```

##### 三大步骤：

```
master将改变记录到二进制日志（binary log）。这些记录过程叫做二进制日志时间，binary log events

slave将master的binary log ebents拷贝到它的中继日志（relay log

slave重做中继日志中的时间，将改变应用到自己的数据库中。MySQL复制是异步的且串行化的
```

![2](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/MySQL/66.png)





### 复制的基本原则

```
每个slave只有一个master

每个slave只能有一个唯一的服务器ID

每个master可以有多个salve
```



### 复制最大问题

##### 延时





## 六、参考资料

##### 尚硅谷讲堂

##### 图灵学院
