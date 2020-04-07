# 目录

* [1.Linux树状目录结构：](#1linux树状目录结构：)
* [2.linux查询某个程序的某个进程的id？](#2linux查询某个程序的某个进程的id？)
* [pidof apache2](#pidof-apache2)
* [pgrep apache2](#pgrep-apache2)
* [pstree -p | grep "apache2"](#pstree--p--grep-apache2)
* [pstree -p | grep "apache2" | head -1](#pstree--p--grep-apache2--head--1)
* [ps aux | grep "apache2"](#ps-aux--grep-apache2)
    * [3.生产上线常用Linux命令：](#3生产上线常用linux命令：)
    * [实例](#实例)
    * [面试题：](#面试题：)
* [参考资料](#参考资料)

### 1.Linux树状目录结构：

![img](https://img2018.cnblogs.com/blog/1368782/201810/1368782-20181013133614828-2003831152.png)

 

### 2.linux查询某个程序的某个进程的id？

　　PID 代表进程标识号process identification，它在大多数操作系统内核（如 Linux、Unix、macOS 和 Windows）中使用。它是在操作系统中创建时自动分配给每个进程的唯一标识号。一个进程是一个正在运行的程序实例。

　　除了 init 进程外，其他所有的进程 ID 每次都会改变，因为 init 始终是系统上的第一个进程，并且是所有其他进程的父进程。它的 PID 是 1。PID 默认的最大值是 32768（2^15）。可以在你的系统上运行 cat /proc/sys/kernel/pid_max 来验证。在 32 位系统上，32768 是最大值，但是我们可以在 64 位系统上将其设置为最大 2^22（约 4 百万）内的任何值。

　　为什么需要这么多的 PID？因为我们不能立即重用 PID。另外为了防止可能的错误。

　　系统正在运行的进程的 PID 可以通过使用 pidof、pgrep、ps 和 pstree 命令找到。

　　**方法 1：使用 pidof 命令**

`　　pidof` 用于查找正在运行的程序的进程 ID。它在标准输出上打印这些 id。为了演示，我们将在 Debian 9（stretch）系统中找出 Apache2 的进程 ID。

```c
# pidof apache2
3754 2594 2365 2364 2363 2362 2361
```

　　从上面的输出中，你可能会遇到难以识别进程 ID 的问题，因为它通过进程名称显示了所有的 PID（包括父进程和子进程）。因此，我们需要找出父 PID（PPID），这是我们要查找的。它可能是第一个数字。在本例中，它是 `3754`，并按降序排列。

　　**方法 2：使用 pgrep 命令**

　　`pgrep` 遍历当前正在运行的进程，并将符合选择条件的进程 ID 列到标准输出中。

```c
# pgrep apache2
2361
2362
2363
2364
2365
2594
3754
```

　　这也与上面的输出类似，但是它将结果从小到大排序，这清楚地说明父 PID 是最后一个。在本例中，它是 `3754`。

　　**注意：** 如果你有多个进程的进程 ID，那么在使用 `pidof` 和 `pgrep` 识别父进程 ID 时就可能不会很顺利。

　　**方法 3：使用 pstree 命令**

`　　pstree` 将运行的进程显示为一棵树。树的根是某个 pid，如果省略了 pid 参数，那么就是 init。如果在 `pstree`命令中指定了用户名，则显示相应用户拥有的所有进程。

`　　pstree` 会将相同的分支放在方括号中，并添加重复计数的前缀来可视化地合并到一起。

```c
# pstree -p | grep "apache2"
 |- apache2(3754) -|-apache2(2361)
 | |-apache2(2362)
 | |-apache2(2363)
 | |-apache2(2364)
 | |-apache2(2365)
 | |-apache2(2594)
```

　　要单独获取父进程，请使用以下格式。

```c
# pstree -p | grep "apache2" | head -1
 |- apache2(3754) -|-apache2(2361)
```

`　　pstree` 命令非常简单，因为它分别隔离了父进程和子进程，但这在使用 `pidof` 和 `pgrep` 时命令不容易做到。

　　**方法 4：使用 ps 命令**

`　　ps` 显示活动进程的选择信息。它显示进程 ID（`pid`=PID）、与进程关联的终端（`tname`=TTY）、以 `[DD-]hh:mm:ss` 格式（`time`=TIME）显示的累计 CPU 时间、以及执行名（`ucmd` = CMD）。输出默认是未排序的。

```c
# ps aux | grep "apache2"
www-data 2361 0.0 0.4 302652 9732 ? S 06:25 0:00 /usr/sbin/apache2 -k start
www-data 2362 0.0 0.4 302652 9732 ? S 06:25 0:00 /usr/sbin/apache2 -k start
www-data 2363 0.0 0.4 302652 9732 ? S 06:25 0:00 /usr/sbin/apache2 -k start
www-data 2364 0.0 0.4 302652 9732 ? S 06:25 0:00 /usr/sbin/apache2 -k start
www-data 2365 0.0 0.4 302652 8400 ? S 06:25 0:00 /usr/sbin/apache2 -k start
www-data 2594 0.0 0.4 302652 8400 ? S 06:55 0:00 /usr/sbin/apache2 -k start
root 3754 0.0 1.4 302580 29324 ? Ss Dec11 0:23 /usr/sbin/apache2 -k start
root 5648 0.0 0.0 12784 940 pts/0 S+ 21:32 0:00 grep apache2
```

　　从上面的输出中，我们可以根据进程的启动日期轻松地识别父进程 ID（PPID）。在此例中，apache2 启动于 `Dec 11`，它是父进程，其他的是子进程。apache2 的 PID 是 `3754`。

### 3.生产上线常用Linux命令：

**1.ls -rlt :**

　　**ls**命令用来显示目标列表，在Linux中是使用率较高的命令。ls命令的输出信息可以进行彩色加亮显示，以分区不同类型的文件。

　　-r：以文件名反序排列并输出目录内容列表；

　　-l：以长格式显示目录下的内容列表。输出的信息从左到右依次包括文件名，文件类型、权限模式、硬连接数、所有者、组、文件大小和文件的最后修改时间等；

　　-s：显示文件和目录的大小，以区块为单位；

**2.tar :**

　　语法：tar(选项)(参数)

　　选项：

　　　　-x或--extract或--get：从备份文件中还原文件；

　　　　-c或--create：建立新的备份文件；

　　　　-v：显示操作过程；

　　　　-f<备份文件>或--file=<备份文件>：指定备份文件；

　　　　-t或--list：列出备份文件的内容；

 

　　参数：文件或目录：指定要打包的文件或目录列表。

　　实例：

　　　　tar -cvf 打包文件名 原文件名 ： 打包文件

　　　　tar -xvf 打包文件名 ： 解压文件

**3.cp ：**

　　语法：cp(选项)(参数)

　　选项：-R/r：递归处理，将指定目录下的所有文件与子目录一并处理；

　　实例：

　　　　cp ../mary/homework/assign .　　将指定文件复制到当前目录下；

　　　　cp file /usr/men/tmp/file1　　将文件file复制到目录`/usr/men/tmp`下，并改名为file1；

　　　　cp -r /usr/men /usr/zh　　将目录`/usr/men`下的所有文件及其子目录复制到目录`/usr/zh`中；

**4.mv :**

　　mv命令可以用来将源文件移至一个目标文件中，或将一组文件移至一个目标目录中。源文件被移至目标文件有两种不同的结果：

1. 如果目标文件是到某一目录文件的路径，源文件会被移到此目录下，且文件名不变。
2. 如果目标文件不是目录文件，则源文件名（只能有一个）会变为此目标文件名，并覆盖己存在的同名文件。如果源文件和目标文件在同一个目录下，mv的作用就是改文件名。当目标文件是目录文件时，源文件或目录参数可以有多个，则所有的源文件都会被移至目标文件中。所有移到该目录下的文件都将保留以前的文件名。

　　注意事项：mv与cp的结果不同，mv好像文件“搬家”，文件个数并未增加。而cp对文件进行复制，文件个数增加了。

　　语法：mv(选项)(参数)

　　实例：

　　　　mv /usr/men/* .　　将目录`/usr/men`中的所有文件移到当前目录（用`.`表示）中；

　　　　mv ex3 new1　　将文件ex3改名为new1；

**5.cat :**

　　cat命令连接文件并打印到标准输出设备上，cat经常用来显示文件的内容，类似于下的type命令。

　　注意：当文件较大时，文本在屏幕上迅速闪过（滚屏），用户往往看不清所显示的内容。因此，一般用more等命令分屏显示。为了控制滚屏，可以按Ctrl+S键，停止滚屏；按Ctrl+Q键可以恢复滚屏。按Ctrl+C（中断）键可以终止该命令的执行，并且返回Shell提示符状态。

　　语法：cat(选项)(参数)

　　选项：

　　　　-n或-number：有1开始对所有输出的行数编号；

　　　　-b或--number-nonblank：和-n相似，只不过对于空白行不编号；

　　　　-s或--squeeze-blank：当遇到有连续两行以上的空白行，就代换为一行的空白行；

　　　　-A：显示不可打印字符，行尾显示“$”；

　　实例：

　　　　cat m1 （在屏幕上显示文件ml的内容）

　　　　cat m1 m2 （同时显示文件ml和m2的内容）

　　　　cat m1 m2 > file （将文件ml和m2合并后放入文件file中）

**6.pwd :**

　　以绝对路径的方式显示用户当前工作目录。命令将当前目录的全路径名称（从根目录）写入标准输出。全部目录使用`/`分隔。第一个`/`表示根目录，最后一个目录是当前目录。执行pwd命令可立刻得知您目前所在的工作目录的绝对路径名称。

　　语法：pwd（选项）

**7.lsblk ：**

　　 查看所有设备挂载情况  （谐音：楼上暴力狂）

**8.df :**

　　磁盘情况查询

　　　　df -h         查询系统整体磁盘使用情况

　　　　df -h /目录     查询指定目录的磁盘占用情况，默认为当前。

**9.ps :**

　　用于报告当前系统的进程状态。可以搭配kill指令随时中断、删除不必要的程序。

　　ps命令是最基本同时也是非常强大的进程查看命令，使用该命令可以确定有哪些进程正在运行和运行的状态、进程是否结束、进程有没有僵死、哪些进程占用了过多的资源等等，总之大部分信息都是可以通过执行该命令得到的。

　　显示的信息选项：

　　　　pid     进程识别号

　　　　TTY     终端机号

　　　　TIME    此进程所消CPU时间

　　　　CMD    正在执行的命令或进程名

　　-A：显示所有程序。
　　-e：此选项的效果和指定"A"选项相同。

　　-f：显示UID,PPIP,C与STIME栏位。

　　ps -a     显示当前终端的所有进程信息

　　ps -u     以用户的格式显示进程信息

　　ps -x     显示后台进程运行的参数

　　ps -aux|grep xxx  用于查看tomcat占用资源情况

　　　　%CPU：进程占用CPU的百分比

　　　　%NEM：进程占用物理内存的百分比

　　　　VSZ：  进程占用的虚拟内存大小（单位：KB）

　　　　RSS：  进程占用的物理内存大小（单位：KB）

　　ps -ef|grep xxx  用于kill对应的进程

　　　　PID：  进程ID

　　　　PPID：父进程ID

**10.vmstat ：**

　　vmstat命令的含义为显示虚拟内存状态（“Viryual Memor Statics”），但是它可以报告关于进程、内存、I/O等系统整体运行状态。

　　语法：vmstat(选项)(参数)

　　选项：

```c
　　-a：显示活动内页；
　　-f：显示启动后创建的进程总数；
　　-m：显示slab信息；
　　-n：头信息仅显示一次；
　　-s：以表格方式显示事件计数器和内存状态；
　　-d：报告磁盘状态；
　　-p：显示指定的硬盘分区状态；
　　-S：输出信息的单位。
```

参数

- 事件间隔：状态信息刷新的时间间隔；
- 次数：显示报告的次数。

### 实例

```c
vmstat 3
procs -----------memory---------- ---swap-- -----io---- --system-- -----cpu------
 r  b   swpd   free   buff  cache   si   so    bi    bo   in   cs us sy id wa st
 0  0    320  42188 167332 1534368    0    0     4     7    1    0  0  0 99  0  0
 0  0    320  42188 167332 1534392    0    0     0     0 1002   39  0  0 100  0  0
 0  0    320  42188 167336 1534392    0    0     0    19 1002   44  0  0 100  0  0
 0  0    320  42188 167336 1534392    0    0     0     0 1002   41  0  0 100  0  0
 0  0    320  42188 167336 1534392    0    0     0     0 1002   41  0  0 100  0  0
```

字段说明：

Procs（进程）

- r: 运行队列中进程数量，这个值也可以判断是否需要增加CPU。（长期大于1）
- b: 等待IO的进程数量。

Memory（内存）

- swpd: 使用虚拟内存大小，如果swpd的值不为0，但是SI，SO的值长期为0，这种情况不会影响系统性能。
- free: 空闲物理内存大小。
- buff: 用作缓冲的内存大小。
- cache: 用作缓存的内存大小，如果cache的值大的时候，说明cache处的文件数多，如果频繁访问到的文件都能被cache处，那么磁盘的读IO bi会非常小。

Swap

- si: 每秒从交换区写到内存的大小，由磁盘调入内存。
- so: 每秒写入交换区的内存大小，由内存调入磁盘。

注意：内存够用的时候，这2个值都是0，如果这2个值长期大于0时，系统性能会受到影响，磁盘IO和CPU资源都会被消耗。有些朋友看到空闲内存（free）很少的或接近于0时，就认为内存不够用了，不能光看这一点，还要结合si和so，如果free很少，但是si和so也很少（大多时候是0），那么不用担心，系统性能这时不会受到影响的。

IO（现在的Linux版本块的大小为1kb）

- bi: 每秒读取的块数
- bo: 每秒写入的块数

注意：随机磁盘读写的时候，这2个值越大（如超出1024k)，能看到CPU在IO等待的值也会越大。

system（系统）

- in: 每秒中断数，包括时钟中断。
- cs: 每秒上下文切换数。

注意：上面2个值越大，会看到由内核消耗的CPU时间会越大。

CPU（以百分比表示）

- us: 用户进程执行时间百分比(user time)

us的值比较高时，说明用户进程消耗的CPU时间多，但是如果长期超50%的使用，那么我们就该考虑优化程序算法或者进行加速。

- sy: 内核系统进程执行时间百分比(system time)

sy的值高时，说明系统内核消耗的CPU资源多，这并不是良性表现，我们应该检查原因。

- wa: IO等待时间百分比

wa的值高时，说明IO等待比较严重，这可能由于磁盘大量作随机访问造成，也有可能磁盘出现瓶颈（块操作）。

- id: 空闲时间百分比

**11.free :**

　　free命令可以显示当前系统未使用的和已使用的内存数目，还可以显示被内核使用的内存缓冲区。

　　语法：free(选项)

　　选项：

```c
　　-b：以Byte为单位显示内存使用情况；
　　-k：以KB为单位显示内存使用情况；
　　-m：以MB为单位显示内存使用情况；
　　-o：不显示缓冲区调节列；
　　-s<间隔秒数>：持续观察内存使用状况；
　　-t：显示内存总和列；
　　-V：显示版本信息。
```

　　实例：free -m

```c
             total       used       free     shared    buffers     cached
Mem:          2016       1973         42          0        163       1497
-/+ buffers/cache:        312       1703
Swap:         4094          0       4094
```

第一部分Mem行解释：

```c
total：内存总数；
used：已经使用的内存数；
free：空闲的内存数；
shared：当前已经废弃不用；
buffers Buffer：缓存内存数；
cached Page：缓存内存数。
```

关系：total = used + free

第二部分(-/+ buffers/cache)解释:

```c
(-buffers/cache) used内存数：第一部分Mem行中的 used – buffers – cached
(+buffers/cache) free内存数: 第一部分Mem行中的 free + buffers + cached
```

可见-buffers/cache反映的是被程序实实在在吃掉的内存，而+buffers/cache反映的是可以挪用的内存总数。

第三部分是指交换分区。

**12.top :**

　　top命令可以实时动态地查看系统的整体运行情况，是一个综合了多方信息监测系统性能和运行信息的实用工具。通过top命令所提供的互动式界面，用热键可以管理。

　　语法：top(选项)

　　选项：

```c
　　-b：以批处理模式操作；
　　-c：显示完整的治命令；
　　-d：屏幕刷新间隔时间；
　　-I：忽略失效过程；
　　-s：保密模式；
　　-S：累积模式；
　　-i<时间>：设置间隔时间；
　　-u<用户名>：指定用户名；
　　-p<进程号>：指定进程；
　　-n<次数>：循环显示的次数。
```

　　**top交互命令**

　　在top命令执行过程中可以使用的一些交互命令。这些命令都是单字母的，如果在命令行中使用了-s选项， 其中一些命令可能会被屏蔽。

```c
　　h：显示帮助画面，给出一些简短的命令总结说明；
　　k：终止一个进程；
　　i：忽略闲置和僵死进程，这是一个开关式命令；
　　q：退出程序；
　　r：重新安排一个进程的优先级别；
　　S：切换到累计模式；
　　s：改变两次刷新之间的延迟时间（单位为s），如果有小数，就换算成ms。输入0值则系统将不断刷新，默认值是5s；
　　f或者F：从当前显示中添加或者删除项目；
　　o或者O：改变显示项目的顺序；
　　l：切换显示平均负载和启动时间信息；
　　m：切换显示内存信息；
　　t：切换显示进程和CPU状态信息；
　　c：切换显示命令名称和完整命令行；
　　M：根据驻留内存大小进行排序；
　　P：根据CPU使用百分比大小进行排序；
　　T：根据时间/累计时间进行排序；
　　w：将当前设置写入~/.toprc文件中。
```

　　实例：

```c
　　top - 09:44:56 up 16 days, 21:23,  1 user,  load average: 9.59, 4.75, 1.92
　　Tasks: 145 total,   2 running, 143 sleeping,   0 stopped,   0 zombie
　　Cpu(s): 99.8%us,  0.1%sy,  0.0%ni,  0.2%id,  0.0%wa,  0.0%hi,  0.0%si,  0.0%st
　　Mem:   4147888k total,  2493092k used,  1654796k free,   158188k buffers
　　Swap:  5144568k total,       56k used,  5144512k free,  2013180k cached
```

　　解释：

- top - 09:44:56[当前系统时间],
- 16 days[系统已经运行了16天],
- 1 user[个用户当前登录],
- load average: 9.59, 4.75, 1.92[系统负载，即任务队列的平均长度]
- Tasks: 145 total[总进程数],
- 2 running[正在运行的进程数],
- 143 sleeping[睡眠的进程数],
- 0 stopped[停止的进程数],
- 0 zombie[冻结进程数],
- Cpu(s): 99.8%us[用户空间占用CPU百分比],
- 0.1%sy[内核空间占用CPU百分比],
- 0.0%ni[用户进程空间内改变过优先级的进程占用CPU百分比],
- 0.2%id[空闲CPU百分比], 0.0%wa[等待输入输出的CPU时间百分比],
- 0.0%hi[],
- 0.0%st[],
- Mem: 4147888k total[物理内存总量],
- 2493092k used[使用的物理内存总量],
- 1654796k free[空闲内存总量],
- 158188k buffers[用作内核缓存的内存量]
- Swap: 5144568k total[交换区总量],
- 56k used[使用的交换区总量],
- 5144512k free[空闲交换区总量],
- 2013180k cached[缓冲的交换区总量],

**13.sudo :**

　　sudo命令用来以其他身份来执行命令，预设的身份为root。在`/etc/sudoers`中设置了可执行sudo指令的用户。若其未经授权的用户企图使用sudo，则会发出警告的邮件给管理员。用户使用sudo时，必须先输入密码，之后有5分钟的有效期限，超过期限则必须重新输入密码。

　　语法：sudo(选项)(参数)

　　选项：

```c
　　-b：在后台执行指令；
　　-h：显示帮助；
　　-H：将HOME环境变量设为新身份的HOME环境变量；
　　-k：结束密码的有效期限，也就是下次再执行sudo时便需要输入密码；。
　　-l：列出目前用户可执行与无法执行的指令；
　　-p：改变询问密码的提示符号；
　　-s<shell>：执行指定的shell；
　　-u<用户>：以指定的用户作为新的身份。若不加上此参数，则预设以root作为新的身份；
　　-v：延长密码有效期限5分钟；
　　-V ：显示版本信息。
```

**14.grep :**

　　grep（global search regular expression(RE) and print out the line，全面搜索正则表达式并把行打印出来）是一种强大的文本搜索工具，它能使用正则表达式搜索文本，并把匹配的行打印出来。

　　选项：

```c
　　-a 不要忽略二进制数据。
　　-A<显示列数> 除了显示符合范本样式的那一行之外，并显示该行之后的内容。
　　-b 在显示符合范本样式的那一行之外，并显示该行之前的内容。
　　-c 计算符合范本样式的列数。
　　-C<显示列数>或-<显示列数>  除了显示符合范本样式的那一列之外，并显示该列之前后的内容。
　　-d<进行动作> 当指定要查找的是目录而非文件时，必须使用这项参数，否则grep命令将回报信息并停止动作。
　　-e<范本样式> 指定字符串作为查找文件内容的范本样式。
　　-E 将范本样式为延伸的普通表示法来使用，意味着使用能使用扩展正则表达式。
　　-f<范本文件> 指定范本文件，其内容有一个或多个范本样式，让grep查找符合范本条件的文件内容，格式为每一列的范本样式。
　　-F 将范本样式视为固定字符串的列表。
　　-G 将范本样式视为普通的表示法来使用。
　　-h 在显示符合范本样式的那一列之前，不标示该列所属的文件名称。
　　-H 在显示符合范本样式的那一列之前，标示该列的文件名称。
　　-i 忽略字符大小写的差别。
　　-l 列出文件内容符合指定的范本样式的文件名称。
　　-L 列出文件内容不符合指定的范本样式的文件名称。
　　-n 在显示符合范本样式的那一列之前，标示出该列的编号。
　　-q 不显示任何信息。
　　-R/-r 此参数的效果和指定“-d recurse”参数相同。
　　-s 不显示错误信息。
　　-v 反转查找。
　　-w 只显示全字符合的列。
　　-x 只显示全列符合的列。
　　-y 此参数效果跟“-i”相同。
　　-o 只输出文件中匹配到的部分。
```

grep命令常见用法

　　在文件中搜索一个单词，命令会返回一个包含**“match_pattern”**的文本行：

```c
　　grep match_pattern file_name
　　grep "match_pattern" file_name
```

　　在多个文件中查找：

```c
　　grep "match_pattern" file_1 file_2 file_3 ...
```

　　输出除之外的所有行 **-v** 选项：

```c
　　grep -v "match_pattern" file_name
```

　　标记匹配颜色 **--color=auto** 选项：

```c
　　grep "match_pattern" file_name --color=auto
```

　　使用正则表达式 **-E** 选项：

```c
　　grep -E "[1-9]+"
　　或
　　egrep "[1-9]+"
```

　　只输出文件中匹配到的部分 **-o** 选项：

```c
　　echo this is a test line. | grep -o -E "[a-z]+\."
　　line.

　　echo this is a test line. | egrep -o "[a-z]+\."
　　line.
```

　　统计文件或者文本中包含匹配字符串的行数 **-c** 选项：

```c
　　grep -c "text" file_name
```

　　输出包含匹配字符串的行数 **-n** 选项：

```c
　　grep "text" -n file_name
　　或
　　cat file_name | grep "text" -n

　　#多个文件
　　grep "text" -n file_1 file_2
```

　　打印样式匹配所位于的字符或字节偏移：

```c
　　echo gun is not unix | grep -b -o "not"
　　7:not

　　#一行中字符串的字符便宜是从该行的第一个字符开始计算，起始值为0。选项 -b -o 一般总是配合使用。
```

　　搜索多个文件并查找匹配文本在哪些文件中：

```c
　　grep -l "text" file1 file2 file3...
```

grep递归搜索文件

　　在多级目录中对文本进行递归搜索：

```c
　　grep "text" . -r -n
　　# .表示当前目录。
```

　　忽略匹配样式中的字符大小写：

```c
　　echo "hello world" | grep -i "HELLO"
　　hello
```

　　选项 **-e** 制动多个匹配样式：

```c
　　echo this is a text line | grep -e "is" -e "line" -o
　　is
　　line

　　#也可以使用-f选项来匹配多个样式，在样式文件中逐行写出需要匹配的字符。
　　cat patfile
　　aaa
　　bbb

　　echo aaa bbb ccc ddd eee | grep -f patfile -o
```

　　在grep搜索结果中包括或者排除指定文件：

```c
　　#只在目录中所有的.php和.html文件中递归搜索字符"main()"
　　grep "main()" . -r --include *.{php,html}

　　#在搜索结果中排除所有README文件
　　grep "main()" . -r --exclude "README"

　　#在搜索结果中排除filelist文件列表里的文件
　　grep "main()" . -r --exclude-from filelist
```

　　使用0值字节后缀的grep与xargs：

```c
　　#测试文件：
　　echo "aaa" > file1
　　echo "bbb" > file2
　　echo "aaa" > file3

　　grep "aaa" file* -lZ | xargs -0 rm

　　#执行后会删除file1和file3，grep输出用-Z选项来指定以0值字节作为终结符文件名（\0），xargs -0 读取输入并用0值字节终结符分隔文件名，然后删除匹配文件，-Z通常和-l结合使用。
```

　　grep静默输出：

```c
　　grep -q "test" filename

　　#不会输出任何信息，如果命令运行成功返回0，失败则返回非0值。一般用于条件测试。
```

　　打印出匹配文本之前或者之后的行：

```c
　　#显示匹配某个结果之后的3行，使用 -A 选项：
　　seq 10 | grep "5" -A 3
　　5
　　6
　　7
　　8

　　#显示匹配某个结果之前的3行，使用 -B 选项：
　　seq 10 | grep "5" -B 3
　　2
　　3
　　4
　　5

　　#显示匹配某个结果的前三行和后三行，使用 -C 选项：
　　seq 10 | grep "5" -C 3
　　2
　　3
　　4
　　5
　　6
　　7
　　8
　　
　　#如果匹配结果有多个，会用“--”作为各匹配结果之间的分隔符：
　　echo -e "a\nb\nc\na\nb\nc" | grep a -A 1
　　a
　　b
　　--
　　a
　　b
```

**15.wc :**

　　wc命令用来计算数字。利用wc指令我们可以计算文件的Byte数、字数或是列数，若不指定文件名称，或是所给予的文件名为“-”，则wc指令会从标准输入设备读取数据。

　　语法：wc(选项)(参数)

　　选项：

```c
　　-c或--bytes或——chars：只显示Bytes数；
　　-l或——lines：只显示列数；
　　-w或——words：只显示字数。
```

　　参数：

　　　　文件：需要统计的文件列表。

　　实例：

　　　　cat mytest.sql | grep ba_0001 | wc -l

**其他：**

　　sudo su：切换到超级管理员
　　cd /：回到当前系统的根目录
　　cd ~：回到当前用户家目录
　　cd -：回到上一次所在目录
　　pwd：打印当前的工作目录
　　ls -l：查看当前目录下所有文件
　　ls -R：递归查看目录里面的内容
　　ls -F：选项会在显示目录条目时，在目录后加一个/
　　ls -ltr：逆序罗列最新修改过的文件
　　VI : 进入编译模式
　　head -n 10 文件名：查看文件头10行
　　tail -n 10 文件名：查看文件后10行
　　head -n 20 /etc/man.config | tail -n 10：显示/etc/man.config的第11到20行
　　tar -zcvf XXX.tar.gz  n1.txt  n2.txt：压缩文件
　　tar -zxvf XXX.tar.gz ： 解压文件
　　rmdir：删除空目录
　　mkdir：创建目录
　　touch：新建文件
　　ps -aux：查看进程，然后再利用一个管道符号导向到grep去查找特定的进程 ,然后再对特定的进程进行操作。
　　ps -ef：是以全格式显示当前所有的进程
　　ifconfig ：查看IP地址信息(网络配置类)
　　fdisk -l | df -h：磁盘分区类
　　top：系统整体性能评估
　　vmstat：检测是否存在CPU瓶颈
　　free:检测是否存在内存瓶颈
　　iostat ：检测是否存在磁盘I/O瓶颈
　　netstat ：检测是否存在网络I/O瓶颈
　　ps：可以查看进程以及进程中cpu的使用情况

### 面试题：

**1.查询Zookeeper服务是否启动的三种方法？**

​     答：① ps -ef | grep zookeeper

​           ps命令用于报告当前系统的进程状态。

​           其中 -e：显示所有程序。

​               -f：显示UID,PPIP,C与STIME栏位。   

​               

​         ② netstat -anp | grep 2181

​          netstat命令用来打印Linux中网络系统的状态信息，可让你得知整个Linux系统的网络情况。

​           其中 -a或--all：显示所有连线中的Socket；

​               -n或--numeric：直接使用[ip](http://man.linuxde.net/ip)地址，而不通过域名服务器；

​               -p或--programs：显示正在使用Socket的程序识别码和程序名称；

​         ③ lsof -i:2181

​           lsof命令用于查看你进程开打的文件，打开文件的进程，进程打开的端口(TCP、UDP)。找回/恢复删除的文件。因为lsof命令需要访问核心内存和各种文件，所以需要root用户执行。

​           其中：-i<条件>：列出符合条件的进程。（4.6.协议、:端口、 @[ip](http://man.linuxde.net/ip) ）

  

**2.说出你日常常用的5个Linux命令？**

​     答：① ps -ef | grep -n zookeeper | grep -v grep | sort -nk2 | awk '{print $2}' 

grep -n zookeeper -n 在显示符合范本样式的那一列之前，标示出该列的编号。即该进程在进程列表中出现的行号。

grep -v grep -v反转查找。即在列出的进程中去除含有关键字(第二个grep)之外的所有进程；

sort -nk2 -n：依照数值的大小排序；

​      -k：依照第几列数值进行操作；

sort -nrk2 -r：以相反的顺序来排序；

awk '{print $2}' awk：一种处理文本文件的语言，是一个强大的文本分析工具。

​          print：格式化并输出结果到标准输出；

​          $：第几列；

​         ② ps -ef | grep -n zookeeper | grep -v grep | wc -l

其中：wc -l wc：即word count用来计算数字，关键字统计计数

​         -c或-bytes或-chars：只显示Bytes数；

​         -l或-lines：只显示列数；

​         -w或-words：只显示字数；

​        ③ ps -ef | grep -n zookeeper | grep -v grep | sort -nk2 | awk '{print $2}'  |  xargs kill -9

其中：xargs kill -9 xargs：给其它命令传递参数的一个过滤器，是组合多个命令的一个工具，即读取左边结果并将其作为参数传入右边命令中。

​          kill -9：第9种信号（sigkill）无条件终止进程。

​             HUP 1 终端断线

​             INT 2 中断（同 Ctrl + C）

​             QUIT 3 退出（同 Ctrl + \）

​             TERM 15 终止

​             KILL 9 强制终止

​             CONT 18 继续（与STOP相反， [fg](http://man.linuxde.net/fg)/[bg](http://man.linuxde.net/bg)命令）

​             STOP 19 暂停（同 Ctrl + Z）

**3.日常工作常用技术的默认端口号？**    

 答：① Tomcat：8080

​     ② zookeeper：2181

​     ③ MySQL

驱动：org.gjt.mm.mysql.Driver

URL：jdbc:mysql://<machine_name><:port>/dbname

注：machine_name：数据库所在的机器的名称，如果是本机则是127.0.0.1或者是localhost，如果是远程连接，则是远程的IP地址；

port：端口号，默认3306

**4.Linux查看磁盘IO的几种方法：**

*第一种：用 top 命令 中的cpu 信息观察：*

　　top可以看到的cpu信息有：

　　Tasks: 29 total, 1 running, 28 sleeping, 0 stopped, 0 zombie

　　Cpu(s): 0.3% us, 1.0% sy, 0.0% ni, 98.7% id, 0.0% wa, 0.0% hi, 0.0% si

　　具体的解释如下：

　　Tasks: 29 total 进程总数

　　1 running 正在运行的进程数

　　28 sleeping 睡眠的进程数

　　0 stopped 停止的进程数

　　0 zombie 僵尸进程数

　　Cpu(s):

　　0.3% us 用户空间占用CPU百分比

　　1.0% sy 内核空间占用CPU百分比

　　0.0% ni 用户进程空间内改变过优先级的进程占用CPU百分比

　　98.7% id 空闲CPU百分比

　　0.0% wa 等待输入输出的CPU时间百分比

　　0.0% hi

　　0.0% si

　　0.0% wa 的百分比可以大致的体现出当前的磁盘io请求是否频繁。如果 wa的数量比较大，说明等待输入输出的的io比较多。

*第二种：用vmstat：*

　　vmstat 命令报告关于线程、虚拟内存、磁盘、陷阱和 CPU 活动的统计信息。由 vmstat 命令生成的报告可以用于平衡系统负载活动。系统范围内的这些统计信息(所有的处理器中)都计算出以百分比表示的平均值，或者计算其总和。

　　输入命令：

　　vmstat 2 5

　　如果发现等待的进程和处在非中断睡眠状态的进程数非常多，并且发送到块设备的块数和从块设备接收到的块数非常大，那就说明磁盘io比较多。

　　vmstat参数解释：

　　Procs

　　r: 等待运行的进程数 b: 处在非中断睡眠状态的进程数 w: 被交换出去的可运行的进程数。此数由 linux 计算得出，但 linux 并不耗尽交换空间

　　Memory

　　swpd: 虚拟内存使用情况，单位：KB

　　free: 空闲的内存，单位KB

　　buff: 被用来做为缓存的内存数，单位：KB

　　Swap

　　si: 从磁盘交换到内存的交换页数量，单位：KB/秒

　　so: 从内存交换到磁盘的交换页数量，单位：KB/秒

　　IO

　　bi: 发送到块设备的块数，单位：块/秒

　　bo: 从块设备接收到的块数，单位：块/秒

　　System

　　in: 每秒的中断数，包括时钟中断

　　cs: 每秒的环境(上下文)切换次数

　　CPU

　　按 CPU 的总使用百分比来显示

　　us: CPU 使用时间

　　sy: CPU 系统使用时间

　　id: 闲置时间

　　准测

　　更多vmstat使用信息

　　第二种：用iostat

　　安装:

　　Iostat 是 sysstat 工具集的一个工具，需要安装。

　　Centos的安装方式是：

　　yum install sysstat

　　Ubuntu的安装方式是：

　　aptitude install sysstat

　　使用：

　　iostat -dx 显示磁盘扩展信息

　　root@fileapp:~# iostat -dx

　　r/s 和 w/s 分别是每秒的读操作和写操作，而rKB/s 和wKB/s 列以每秒千字节为单位显示了读和写的数据量

　　如果这两对数据值都很高的话说明磁盘io操作是很频繁。

 

**5.查看整机性能：**

总-分-总

1 查看整机性能

  1.1  top  

  load average: 0.05, 0.05, 0.05

  负载均衡值

  3个值

  分别是1/5/15分钟系统的平均负载

  三值相加/3 高于60%，系统 紧张资源

  

  1.2  能做不能说

  1.3  其它??

​    zombie 高 越大系统越差

  1.4  id不是主键

​    id = idle （空闲率）此值越高越好

  1.5  还有吗？

  1.6  简单版？？？-----》

  1.7  ctrl+c不用，用q = quit

2   整机性能的简单命令

  uptime

3  cpu(包含但是不限于)

  vmstat -n 2 3

4  内存

  free -m

5  硬盘

  df -h

6  硬盘IO

  

\* tps  每秒钟事务提交数

  qps  每秒钟查询数

  pv  pageView 页面浏览数

others 文件权限    chmod 777

  网络端口    

top+ uptime+vmstat+free+df+iostat+chmod

新知识

cat nginxerr.log |awk '{print$7}'|sort|uniq -dc|head -n 1

http://man.linuxde.net/

### 参考资料：
https://www.cnblogs.com/jasonZh/p/9513646.html
