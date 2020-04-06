# 目录

* [一JavaIO流的分类](#一javaio流的分类)
    * [输入流和输出流](#输入流和输出流)
    * [字节流和字符流](#字节流和字符流)
    * [节点流和处理流](#节点流和处理流)
* [二InputStream，OutputStream，Reader和Writer](#二inputstream，outputstream，reader和writer)
* [三节点流](#三节点流)
* [四处理流](#四处理流)
    * [缓冲流](#缓冲流)
    * [转换流](#转换流)
    * [数据流](#数据流)
    * [Print流](#print流)
    * [Object流](#object流)
* [五流操作的典型示例](#五流操作的典型示例)
* [六概述](#六概述)  
      * [**Channel主要实现：**](#channel主要实现：)  
      * [**Buffer主要实现：**](#buffer主要实现：)  
      * [**Selector：**](#selector：)  
* [七Channel介绍](#七channel介绍)  
      * [**Channel与流对比：**](#channel与流对比：)  
      * [**Channel的主要实现类：**](#channel的主要实现类：)  
      * [**示例：**](#示例：)  
* [八Buffer介绍](#八buffer介绍)  
      * [**1Buffer的基本用法**](#1buffer的基本用法)  
      * [**2capacity、position、limit、（mark）：**](#2capacity、position、limit、（mark）：)  
      * [**3类型：**](#3类型：)  
      * [**4分配（allocate）：**](#4分配（allocate）：)  
      * [**5向Buffer中写数据：**](#5向buffer中写数据：)  
      * [**6从Buffer中读取数据：**](#6从buffer中读取数据：)  
      * [**7其他方法：**](#7其他方法：)  
* [九Scatter/Gather](#九scattergather)  
      * [**1Scattering Reads：**](#1scattering-reads：)  
      * [**2Gathering Writes：**](#2gathering-writes：)  
* [十通道之间的数据传输](#十通道之间的数据传输)  
      * [**1transferFrom()：**](#1transferfrom：)  
      * [**2transferTo()：**](#2transferto：)  
* [十一Selector](#十一selector)  
      * [**1为什么使用Selector：**](#1为什么使用selector：)  
      * [**2Selector的创建：**](#2selector的创建：)  
      * [**3向Selector注册通道：**](#3向selector注册通道：)  
      * [**4SelectionKey：**](#4selectionkey：)  
      * [**5通过Selector选择通道：**](#5通过selector选择通道：)  
      * [**6wakeUp()：**](#6wakeup：)  
      * [**7close()：**](#7close：)  
      * [**8完整的示例：**](#8完整的示例：)  
* [十二FileChannel](#十二filechannel)  
      * [**1打开FileChannel：**](#1打开filechannel：)  
      * [**2从FileChannel读取数据：**](#2从filechannel读取数据：)  
      * [**3向FileChannel写数据：**](#3向filechannel写数据：)  
      * [**4关闭FileChannel：**](#4关闭filechannel：)  
      * [**5FileChannel的position方法：**](#5filechannel的position方法：)  
      * [**6FileChannel的size方法：**](#6filechannel的size方法：)  
      * [**7FileChannel的truncate方法：**](#7filechannel的truncate方法：)  
      * [**8FileChannel的force方法：**](#8filechannel的force方法：)  
* [十三SocketChannel](#十三socketchannel)  
      * [**1打开 SocketChannel：**](#1打开-socketchannel：)  
      * [**2关闭 SocketChannel：**](#2关闭-socketchannel：)  
      * [**3从 SocketChannel 读取数据：**](#3从-socketchannel-读取数据：)  
      * [**4写入 SocketChannel：**](#4写入-socketchannel：)  
      * [**5非阻塞模式：**](#5非阻塞模式：)  
      * [**6非阻塞模式与选择器：**](#6非阻塞模式与选择器：)  
* [十四ServerSocketChannel](#十四serversocketchannel)  
      * [**1打开 ServerSocketChannel：**](#1打开-serversocketchannel：)  
      * [**2关闭 ServerSocketChannel：**](#2关闭-serversocketchannel：)  
      * [**3监听新进来的连接：**](#3监听新进来的连接：)  
      * [**4非阻塞模式：**](#4非阻塞模式：)  
* [十五DatagramChannel](#十五datagramchannel)  
      * [**1打开 DatagramChannel：**](#1打开-datagramchannel：)  
      * [**2接收数据：**](#2接收数据：)  
      * [**3发送数据：**](#3发送数据：)  
      * [**4连接到特定的地址：**](#4连接到特定的地址：)  
      * [**5例子:：**](#5例子：)  
* [十六DatagramChannel](#十六datagramchannel)  
      * [**1创建管道：**](#1创建管道：)  
      * [**2向管道写数据：**](#2向管道写数据：)  
      * [**3从管道读取数据：**](#3从管道读取数据：)  
* [十七NIO与IO](#十七nio与io)
* [十八参考资料](#十八参考资料)



# 一JavaIO流的分类

在Java中，对于数据的输入/输出（I/O）操作以“流”（stream）方式进行，数据的来源可以是文件、网络、内存缓存等。 

J2SDK提供了各种各样的“流”，用以操作不同种类的数据，J2SDK所提供的所有流都位于java.io包内，从不同的角度对这些流进行归类：

```java
按数据流向不同可以分为输入流和输出流。
按处理数据单位不同可以分为字节流和字符流。
按照流类的功能不同可以分为节点流和处理流。 
```

 ![img](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/JavaIO%26NIO/1.png) 



### 输入流和输出流

流具有方向性，至于是输入流还是输出流则是一个相对的概念，一般是站在程序的角度去判断，从数据源读取数据到程序中的是输入流，相反从程序中向外写数据的是输出流。



### 字节流和字符流

字节流以字节（byte，1byte=8bit）为单位，一个字节一个字节的读写数据。从上图可以看出，所有的字节流均继承自抽象类InputStream/OutputStream。

字符流以字符（Java中，一个字符为2byte=16bit）为单位，一个字符一个字符的读写数据。所有的字符流均继承自抽象类Reader/Writer。



### 节点流和处理流

节点流为直接与数据源（节点）相连，可以从数据源读写数据的流。

 ![img](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/JavaIO%26NIO/2.png) 

处理流是“连接”在已存在的流（节点流或处理流）之上，可以为程序提供更加强大的读写操作功能的流。 

 ![img](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/JavaIO%26NIO/3.png) 





# 二InputStream，OutputStream，Reader和Writer

**InputStream**是所有字节输入流的超类，其包含的基本方法如下。 

```java
// 从输入流中读取一个字节。并以整数的形式返回（0-255）。
// 如果读取前已经到了输入流的末尾返回-1。
int read() throws IOException;
 
// 从输入流中读取一定数量的字节，并将其存储在缓冲区数组 b中。以整数形式返回实际读取的字节数。
// 如果读取前已经到了输入流的末尾返回-1。
int read(byte[] b) throws IOException;
 
// 读取输入流中len个字节到数组 b中，并将读取的第一个字节存储在元素 b[off] 中，下一个存储在 b[off+1] 中，依次类推。
// 读取的字节数最多等于 len，也可能小于该值。以整数形式返回实际读取的字节数。
// 如果读取前已经到了输入流的末尾返回-1。
int read(byte[] b, int off, int len) throws IOException;
 
// 跳过和丢弃此输入流中数据的 n个字节。返回跳过的实际字节数。
// 如果 n为负数，则不跳过任何字节。
long skip(long n) throws IOException;
 
// 返回此输入流下一个方法调用可以不受阻塞地从此输入流读取（或跳过）的估计字节数。
// 如果到达输入流末尾，则返回 0。
int available() throws IOException;
 
// 关闭此输入流并释放与该流关联的所有系统资源。
void close() throws IOException;
 
// 在此输入流中标记当前的位置。调用reset()方法会根据此标记的位置重新定位此流，以便后续重新读取相同的字节。
// readlimit参数告知此输入流在标记位置失效之前允许读取的字节数。
void mark(int readlimit);
 
// 将此流重新定位到最后一次对此输入流调用 mark()方法时的位置。
void reset() throws IOException;
 
// 测试此输入流是否支持 mark()和 reset() 方法。
// 是否支持 mark()和 reset()是特定输入流实例的不变属性。
boolean markSupported();
```


**OutputStream**是所有字节输出流的超类，其包含的基本方法如下。

```java
// 将指定的字节写入此输出流。
// 要写入的字节是参数 b的低8位，b的 24 个高位将被忽略。
void write(int b) throws IOException;
 
// 将一个字节数组中的数据写入输出流。
void write(byte[] b) throws IOException;
 
// 将指定 byte数组中从偏移量 off 开始的 len 个字节写入此输出流。
void write(byte[] b, int off, int len) throws IOException;
 
// 刷新此输出流并强制将输出流中缓存的数据写出。
void flush() throws IOException;
 
// 关闭此输出流并释放与此流有关的所有系统资源。
void close() throws IOException;
```


**Reader**是所有字符输入流的超类，其包含的基本方法如下。

```java
// 试图将字符读入指定的字符缓冲区。
// 返回添加到缓冲区中的字符数量。
int read(CharBuffer target) throws IOException;
 
// 读取单个字符，并以整数的形式返还，范围在 0 到 65535 之间。
// 如果读取前已经到了输入流的末尾返回-1.
int read() throws IOException;
 
// 将字符读入数组cbuf，并返回读取的字符数。
// 如果读取前已经到了输入流的末尾返回-1.
int read(char[] cbuf) throws IOException;
 
// 读取输入流中len个字符到数组 cbuf中,并将读取的第一个字符存储在元素cbuf[off]中，下一个存储在 cbuf[off+1]中，依次类推。
// 读取的字符数最多等于 len，也可能小于该值。以整数形式返回实际读取的字符数。
// 如果读取前已经到了输入流的末尾返回-1.
int read(char[] cbuf, int off, int len) throws IOException;
 
// 跳过和丢弃此输入流中数据的 n个字符。返回跳过的实际字符数。
// 如果 n 为负数，则不跳过任何字符。
long skip(long n) throws IOException;
 
// 判断是否准备读取此流。
boolean ready() throws IOException;
 
// 关闭此输入流并释放与该流关联的所有系统资源。
void close() throws IOException;
 
// 在此输入流中标记当前的位置。调用reset()方法会根据此标记的位置重新定位此流，以便后续重新读取相同的字符。
// readlimit参数告知此输入流在标记位置失效之前允许读取的字符数。
void mark(int readlimit);
 
// 将此流重新定位到最后一次对此输入流调用 mark()方法时的位置。
void reset() throws IOException;
 
// 测试此输入流是否支持 mark()和 reset() 方法。
// 是否支持 mark()和 reset()是特定输入流实例的不变属性。
boolean markSupported();
```


**Writer**是所有字符输出流的超类，其包含的基本方法如下。

```java
// 将指定的字符写入此输出流。
// 要写入的字符是参数 c的低16位。c的 16 个高位将被忽略。
void write(int c) throws IOException;
 
// 将一字符数组中的数据写入输出流。
void write(char[] cbuf) throws IOException;
 
// 将指定 cbuf数组中从偏移量 off 开始的 len 个字符写入此输出流。
void write(char[] cbuf, int off, int len) throws IOException;
 
// 将字符串str写入此输出流。
void write(String str) throws IOException;
 
// 将字符串str中从off开始的len个字符写入此输出流。
void write(String str, int off, int len) throws IOException;
 
// 将指定字符序列添加到此 writer。
Writer append(CharSequence csq) throws IOException;
 
// 将指定字符序列中索引从start到end之间的字符写入到此输出流。
Writer append(CharSequence csq, int start, int end) throws IOException;
 
// 将指定字符添加到此 writer。
Writer append(char c) throws IOException;
 
// 刷新此输出流并强制将输出流中缓冲的数据写出。
void flush() throws IOException;
 
// 关闭此输出流并释放与此流有关的所有系统资源。
void close() throws IOException;
```


# 三节点流

根据数据来源不同对节点流进行分类如下图。

![1586136234060](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/JavaIO%26NIO/4.png)



# 四处理流  

### 缓冲流

J2SDK提供了四种缓冲流：**BufferedReader**、**BufferedWriter**、**BufferedInputStream**和**BufferedOutputStream**。缓冲流对读写的数据提供了缓冲的功能，可以提高读写的效率，同时还增加了一些新的方法。

BufferedReader新增了readLine()方法用于读取一行字符串（以\r或\n分隔）。

BufferedWriter新增了newLine()方法用于写入一个分隔符。



```java
public static void main(String[] args) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(
					"D:\\io\\bufferedTest.txt"));
			String s;
			for (int i = 0; i < 10; i++) {
				s = String.valueOf(Math.random());
				bw.write(s);
				bw.newLine();
			}
			bw.flush();
			bw.close();		
		Reader reader = new FileReader("D:\\io\\bufferedTest.txt");
		BufferedReader br = new BufferedReader(reader);
		while ((s = br.readLine()) != null) {
			System.out.println(s);
		}
		br.close();
	} catch (FileNotFoundException e) {
		System.out.println("找不到指定文件!");
		System.exit(-1);
	} catch (IOException e) {
		System.out.println("系统错误！");
		System.exit(-1);
	}
}
```


### 转换流

J2SDK提供了**InputStreamReader**和**OutputStreamWriter**两个转换流，用于将字节流转换为字符流。



```java
public static void main(String[] args) {
		try {
			OutputStream outStream = new FileOutputStream(
					"D:\\io\\transformIo.txt");
			OutputStreamWriter outWriter = new OutputStreamWriter(outStream);
			outWriter.write("2017年3月25日风雨大作。。。");
			outWriter.close();
			outWriter = new OutputStreamWriter(new FileOutputStream(
				"D:\\io\\transformIo.txt", true), "GBK");
		outWriter.write("2017年3月26日电闪雷鸣。。。");
		outWriter.close();
	} catch (FileNotFoundException e) {
		System.out.println("找不到指定文件!");
		System.exit(-1);
	} catch (UnsupportedEncodingException e) {
		System.out.println("编码异常！");
		System.exit(-1);
 
	} catch (IOException e) {
		System.out.println("写入错误！");
		System.exit(-1);
	}
 
	System.out.println("写入成功！");
}
```


### 数据流

J2SDK提供了**DataInputStream**和**DataOutputStream**两个数据流，用以提供存取与机器无关的Java原始数据类型数据（如：int，double等）的方法。



```java
public static void main(String[] args) {
		try {
			ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
			DataOutputStream dataOut = new DataOutputStream(byteOut);
			dataOut.writeChar(65);
			dataOut.writeUTF("B");
			dataOut.writeBoolean(false);
			dataOut.writeChars("CD");
			dataOut.writeFloat(0.1111f);
			dataOut.close();
			DataInputStream dataIn = new DataInputStream(
				new ByteArrayInputStream(byteOut.toByteArray()));
		System.out.println(dataIn.readChar());
		System.out.println(dataIn.readUTF());
		System.out.println(dataIn.readBoolean());
		System.out.println(dataIn.readChar());
		System.out.println(dataIn.readChar());
		System.out.println(dataIn.readFloat());
 
	} catch (IOException e) {
		System.out.println("写入错误！");
		System.exit(-1);
	}
 
}
```


### Print流

J2SDK提供了**PrintWriter**和**PrintStream**两个Print流，提供了重载的print()和println()方法用于多种数据类型的输出。

```java
public static void main(String[] args) {
	try {
		FileOutputStream in = new FileOutputStream(
				"D:\\io\\PrintIoTest.txt");
		PrintStream printer = new PrintStream(in);
		System.setOut(printer);
 
		BufferedReader br = new BufferedReader(new InputStreamReader(
				System.in));
		String s;
		while ((s = br.readLine()) != null) {
			if (s.equalsIgnoreCase("exit")) {
				break;
			}
			System.out.println(s);
		}
 
	} catch (IOException e) {
		System.out.println("系统错误！");
		System.exit(-1);
	}
}
```


### Object流

J2SDK提供了**ObjectInputStream**和**ObjectOutputStream**两个Object流，用于将实现了Serializable接口的对象实例直接以二进制的形式进行存取的方法。



```java
import java.io.Serializable;

class User implements Serializable {
	private static final long serialVersionUID = 1L;
	String userName;
	int userAge;
	transient String phoneNum;
}
	public static void main(String[] args) {
		User user = new User();
		user.phoneNum = "0756-8888888";
		user.userAge = 17;
		user.userName = "弄玉";
		OutputStream os;
		try {
			os = new FileOutputStream("D:\\io\\ObjectIoTest.txt");
			ObjectOutputStream oos = new ObjectOutputStream(os);
			oos.writeObject(user);
			oos.flush();
			oos.close();
			FileInputStream fis = new FileInputStream(
				"D:\\io\\ObjectIoTest.txt");
		ObjectInputStream ois = new ObjectInputStream(fis);
		user = (User) ois.readObject();
		System.out.println(user.userName + " " + user.userAge + " "
				+ user.phoneNum);
		ois.close();
	} catch (FileNotFoundException e) {
		System.out.println("找不到指定文件!");
		System.exit(-1);
	} catch (ClassNotFoundException e) {
		System.out.println("类型错误!");
		System.exit(-1);
	} catch (IOException e) {
		System.out.println("系统错误！");
		System.exit(-1);
	}
 
}
```
对于对象中使用**transient**关键字修饰的属性在序列化时将被忽略。对象还可以通过实现**Externalizable**接口，并实现writeExternal()和readExternal()方法来自己指定序列化和反序列化的实现过程。



# 五流操作的典型示例



```java
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileOperator {
private static final int bytes = 1024 * 1024;
 
/**
 * 文件复制
 * @param sourcePath 文件源路径，例如：“D:\\IO\text.txt”
 * @param targetPath 目标文件路径
 */
public static void copyFile(String sourcePath, String targetPath) {
	File sourceFile = new File(sourcePath);
	long targetSize = 0;
	try {
		byte[] buffer = new byte[bytes];
		InputStream inStream = new FileInputStream(sourceFile); // 读入源文件
		OutputStream outStream = new FileOutputStream(targetPath);
		int len;
		while ((len = inStream.read(buffer)) > 0) {
			outStream.write(buffer, 0, len);
			targetSize += len;
		}
		outStream.close();
		inStream.close();
	} catch (FileNotFoundException e) {
		System.out.println("系统找不到指定文件：" + sourcePath);
		System.exit(-1);
	} catch (IOException e) {
		System.out.println("文件复制发生错误，复制失败！");
		System.exit(-1);
	}
	System.out.println("复制完成：");
	System.out.println("源文件大小：" + sourceFile.length());
	System.out.println("复制大小：" + targetSize);
}
 
public static void main(String[] args) {
	String sourcePath = "D:\\io\\";
	String targetPath = "D:\\a";
	File file=new File(sourcePath);
	System.out.println(file.getAbsolutePath());
	System.out.println(file.getPath());
	System.out.println(file.getName());
	System.out.println(file.getParent());
	copyFolder(sourcePath, targetPath);
}
 
/**
 * 复制文件夹
 * @param sourcePath 文件夹源路径，例如:“D:\\IO”
 * @param targetPath 目标文件夹路径
 */
public static void copyFolder(String sourcePath, String targetPath) {
 
		File sourceFile = new File(sourcePath);
		File targetFolder=new File(targetPath);
		if(!targetFolder.exists()){
			targetFolder.mkdirs();
		}
		if(sourceFile.isDirectory()){
			File[] files=sourceFile.listFiles();
			for(File file:files){
				if(file.isDirectory()){
				copyFolder(file.getAbsolutePath(), targetPath+File.separator+file.getName());}
				else{
					copyFile(file.getAbsolutePath(),targetPath+File.separator+file.getName());
				}
			}
		}
		else{
			copyFile(sourcePath,targetPath+File.separator+sourceFile.getName());
		}
		
	}
}
```


# 六概述

java NIO 开始于JDK1.4，其核心元件有：**Channel、Buffer、Selector**。
Channel可以理解为标准IO中流，数据可以从Channel读到Buffer中，也可以从Buffer 写到Channel中。

#### **Channel主要实现：**

**FileChannel、SocketChannel、ServerSocketChannel、DatagramChannel**。

#### **Buffer主要实现：**

**ByteBuffer、CharBuffer、DoubleBuffer、FloatBuffer、IntBuffer、LongBuffer、ShortBuffer**，分别对应基本数据类型：byte, char, double, float, int, long, 和 short。

#### **Selector：**

Selector允许单线程处理多个 Channel。如果你的应用打开了多个连接（通道），但每个连接的流量都很低，使用Selector就会很方便。例如，在一个聊天服务器中。
这是在一个单线程中使用一个Selector处理3个Channel的图示：
![这里写图片描述](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/JavaIO%26NIO/5.png)
要使用Selector，得向Selector注册Channel，然后调用它的select()方法。这个方法会一直阻塞到某个注册的通道有事件就绪。一旦这个方法返回，线程就可以处理这些事件，事件的例子有如新连接进来，数据接收等。



# 七Channel介绍

#### **Channel与流对比：**

- 既可以从Channel(通道)中读取数据，又可以写数据到Channel。但流的读写通常是单向的。
- Channel可以异步地读写。
- Channel中的数据总是要先读到一个Buffer，或者总是要从一个Buffer中写入。

#### **Channel的主要实现类：**

- FileChannel 从文件中读写数据
- SocketChannel 能通过TCP读写网络中的数据
- ServerSocketChannel可以监听新进来的TCP连接，像Web服务器那样。对每一个新进来的连接都会创建一个SocketChannel
- DatagramChannel 能通过UDP读写网络中的数据

#### **示例：**

```java
    RandomAccessFile aFile = new RandomAccessFile("data/nio-data.txt", "rw");
    FileChannel inChannel = aFile.getChannel();

    ByteBuffer buf = ByteBuffer.allocate(48);
    int bytesRead = inChannel.read(buf);

    while (bytesRead != -1) {
        System.out.println("Read " + bytesRead);
        buf.flip(); //首先读取数据到Buffer，然后反转Buffer,接着再从Buffer中读取数据

        while(buf.hasRemaining()){
            System.out.print((char) buf.get());
        }

        buf.clear();
        bytesRead = inChannel.read(buf);
    }
    aFile.close();
```



# 八Buffer介绍

缓冲区本质上是一块可以写入数据，然后可以从中读取数据的内存。这块内存被包装成NIO Buffer对象，并提供了一组方法，用来方便的访问该块内存。

#### **1Buffer的基本用法**

**Buffer读写数据步骤：**
\- 写入数据到Buffer
\- 调用flip()方法
\- 从Buffer中读取数据
\- 调用clear()方法或者compact()方法

当向buffer写入数据时，buffer会记录下写了多少数据。一旦要读取数据，需要通过flip()方法将Buffer从写模式切换到读模式。在读模式下，可以读取之前写入到buffer的所有数据。

一旦读完了所有的数据，就需要清空缓冲区，让它可以再次被写入。有两种方式能清空缓冲区：调用clear()或compact()方法。clear()方法会清空整个缓冲区。compact()方法只会清除已经读过的数据。任何未读的数据都被移到缓冲区的起始处，新写入的数据将放到缓冲区未读数据的后面。

下面是一个使用Buffer的例子：

```java
RandomAccessFile aFile = new RandomAccessFile("data/nio-data.txt", "rw");
FileChannel inChannel = aFile.getChannel();
//create buffer with capacity of 48 bytes
ByteBuffer buf = ByteBuffer.allocate(48);

int bytesRead = inChannel.read(buf); //read into buffer.
while (bytesRead != -1) {
  buf.flip();  //make buffer ready for read

  while(buf.hasRemaining()){
      System.out.print((char) buf.get()); // read 1 byte at a time
  }

  buf.clear(); //make buffer ready for writing
  bytesRead = inChannel.read(buf);
}
aFile.close();
```

#### **2capacity、position、limit、（mark）：**

position和limit的含义取决于Buffer处在读模式还是写模式。不管Buffer处在什么模式，capacity的含义总是一样的。

这里有一个关于capacity，position和limit在读写模式中的说明，详细的解释在插图后面。
![这里写图片描述](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/JavaIO%26NIO/6.png)

**capacity**

作为一个内存块，Buffer有一个固定的大小值，也叫“capacity”.你只能往里写capacity个byte、long，char等类型。一旦Buffer满了，需要将其清空（通过读数据或者清除数据）才能继续写数据往里写数据。

**position**

当你写数据到Buffer中时，position表示当前的位置。初始的position值为0.当一个byte、long等数据写到Buffer后， position会向前移动到下一个可插入数据的Buffer单元。position最大可为capacity – 1.

当读取数据时，也是从某个特定位置读。当将Buffer从写模式切换到读模式，position会被重置为0. 当从Buffer的position处读取数据时，position向前移动到下一个可读的位置。

**limit**

在写模式下，Buffer的limit表示你最多能往Buffer里写多少数据。 写模式下，limit等于Buffer的capacity。

当切换Buffer到读模式时， limit表示你最多能读到多少数据。因此，当切换Buffer到读模式时，limit会被设置成写模式下的position值。换句话说，你能读到之前写入的所有数据（limit被设置成已写数据的数量，这个值在写模式下就是position）

#### **3类型：**

见上边叙述…

#### **4分配（allocate）：**

要想获得一个Buffer对象首先要进行分配。 每一个Buffer类都有一个allocate方法。下面是一个分配48字节capacity的ByteBuffer的例子。

```java
ByteBuffer buf = ByteBuffer.allocate(48);
```

这是分配一个可存储1024个字符的CharBuffer：

```java
CharBuffer buf = CharBuffer.allocate(1024);
```

#### **5向Buffer中写数据：**

写数据到Buffer有两种方式：
\- 从Channel写到Buffer
\- 通过Buffer的put()方法写到Buffer里

从Channel写到Buffer的例子:

```java
int bytesRead = inChannel.read(buf); //read into buffer.
```

通过put方法写Buffer的例子：

```java
buf.put(127);
```

put方法有很多版本，允许你以不同的方式把数据写入到Buffer中。例如， 写到一个指定的位置，或者把一个字节数组写入到Buffer。 更多Buffer实现的细节参考JavaDoc。

**flip()方法：**
flip方法将Buffer从写模式切换到读模式。调用flip()方法会将position设回0，并将limit设置成之前position的值。

换句话说，position现在用于标记读的位置，limit表示之前写进了多少个byte、char等 —— 现在能读取多少个byte、char等。

#### **6从Buffer中读取数据：**

从Buffer中读取数据有两种方式：
**-** 从Buffer读取数据到Channel。
**-** 使用get()方法从Buffer中读取数据。

从Buffer读取数据到Channel的例子：

```java
//read from buffer into channel.
int bytesWritten = inChannel.write(buf);
```

使用get()方法从Buffer中读取数据的例子：

```java
byte aByte = buf.get();
```

get方法有很多版本，允许你以不同的方式从Buffer中读取数据。例如，从指定position读取，或者从Buffer中读取数据到字节数组。更多Buffer实现的细节参考JavaDoc。

#### **7其他方法：**

**rewind()方法：**
Buffer.rewind()将position设回0，所以你可以重读Buffer中的所有数据。limit保持不变，仍然表示能从Buffer中读取多少个元素（byte、char等）。

**clear()与compact()方法：**
一旦读完Buffer中的数据，需要让Buffer准备好再次被写入。可以通过clear()或compact()方法来完成。

如果调用的是clear()方法，position将被设回0，limit被设置成 capacity的值。换句话说，Buffer 被清空了。Buffer中的数据并未清除，只是这些标记告诉我们可以从哪里开始往Buffer里写数据。

如果Buffer中有一些未读的数据，调用clear()方法，数据将“被遗忘”，意味着不再有任何标记会告诉你哪些数据被读过，哪些还没有。

如果Buffer中仍有未读的数据，且后续还需要这些数据，但是此时想要先先写些数据，那么使用compact()方法。

compact()方法将所有未读的数据拷贝到Buffer起始处。然后将position设到最后一个未读元素正后面。limit属性依然像clear()方法一样，设置成capacity。现在Buffer准备好写数据了，但是不会覆盖未读的数据。

**mark()与reset()方法：**
通过调用Buffer.mark()方法，可以标记Buffer中的一个特定position。之后可以通过调用Buffer.reset()方法恢复到这个position。例如：

```java
buffer.mark();
//call buffer.get() a couple of times, e.g. during parsing.
buffer.reset();  //set position back to mark.
```

**equals()方法：**
可以使用equals()和compareTo()方法两个Buffer。

当满足下列条件时，表示两个Buffer相等：
\- 有相同的类型（byte、char、int等）。
\- Buffer中剩余的byte、char等的个数相等。
\- Buffer中所有剩余的byte、char等都相同。

如你所见，equals只是比较Buffer的一部分，不是每一个在它里面的元素都比较。实际上，它只比较Buffer中的剩余元素。

**ompareTo()方法：**
compareTo()方法比较两个Buffer的剩余元素(byte、char等)， 如果满足下列条件，则认为一个Buffer“小于”另一个Buffer：
\- 第一个不相等的元素小于另一个Buffer中对应的元素 。
\- 所有元素都相等，但第一个Buffer比另一个先耗尽(第一个Buffer的元素个数比另一个少)。
（译注：剩余元素是从 position到limit之间的元素）



# 九Scatter/Gather

分散（scatter）从Channel中读取是指在读操作时将读取的数据写入多个buffer中。因此，Channel将从Channel中读取的数据“分散（scatter）”到多个Buffer中。

聚集（gather）写入Channel是指在写操作时将多个buffer的数据写入同一个Channel，因此，Channel 将多个Buffer中的数据“聚集（gather）”后发送到Channel。

scatter / gather经常用于需要将传输的数据分开处理的场合，例如传输一个由消息头和消息体组成的消息，你可能会将消息体和消息头分散到不同的buffer中，这样你可以方便的处理消息头和消息体。

#### **1Scattering Reads：**

Scattering Reads是指数据从一个channel读取到多个buffer中。如下图描述：
![这里写图片描述](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/JavaIO%26NIO/7.png)
代码示例如下：

```java
ByteBuffer header = ByteBuffer.allocate(128);
ByteBuffer body   = ByteBuffer.allocate(1024);

ByteBuffer[] bufferArray = { header, body };

channel.read(bufferArray);
```

注意buffer首先被插入到数组，然后再将数组作为channel.read() 的输入参数。read()方法按照buffer在数组中的顺序将从channel中读取的数据写入到buffer，当一个buffer被写满后，channel紧接着向另一个buffer中写。

Scattering Reads在移动下一个buffer前，必须填满当前的buffer，这也意味着它不适用于动态消息(译者注：消息大小不固定)。换句话说，如果存在消息头和消息体，消息头必须完成填充（例如 128byte），Scattering Reads才能正常工作。

#### **2Gathering Writes：**

Gathering Writes是指数据从多个buffer写入到同一个channel。如下图描述：
![这里写图片描述](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/JavaIO%26NIO/8.png)

代码示例如下：

```java
ByteBuffer header = ByteBuffer.allocate(128);
ByteBuffer body   = ByteBuffer.allocate(1024);
//write data into buffers
ByteBuffer[] bufferArray = { header, body };
channel.write(bufferArray);
```

buffers数组是write()方法的入参，write()方法会按照buffer在数组中的顺序，将数据写入到channel，注意只有position和limit之间的数据才会被写入。因此，如果一个buffer的容量为128byte，但是仅仅包含58byte的数据，那么这58byte的数据将被写入到channel中。因此与Scattering Reads相反，Gathering Writes能较好的处理动态消息。



# 十通道之间的数据传输

#### **1transferFrom()：**

FileChannel的transferFrom()方法可以将数据从源通道传输到FileChannel中（译者注：这个方法在JDK文档中的解释为将字节从给定的可读取字节通道传输到此通道的文件中）。下面是一个简单的例子：

```java
RandomAccessFile fromFile = new RandomAccessFile("fromFile.txt", "rw");
FileChannel      fromChannel = fromFile.getChannel();

RandomAccessFile toFile = new RandomAccessFile("toFile.txt", "rw");
FileChannel      toChannel = toFile.getChannel();

long position = 0;
long count = fromChannel.size();

toChannel.transferFrom(position, count, fromChannel);
```

方法的输入参数position表示从position处开始向目标文件写入数据，count表示最多传输的字节数。如果源通道的剩余空间小于 count 个字节，则所传输的字节数要小于请求的字节数。
此外要注意，在SoketChannel的实现中，SocketChannel只会传输此刻准备好的数据（可能不足count字节）。因此，SocketChannel可能不会将请求的所有数据(count个字节)全部传输到FileChannel中。

#### **2transferTo()：**

transferTo()方法将数据从FileChannel传输到其他的channel中。下面是一个简单的例子：

```java
RandomAccessFile fromFile = new RandomAccessFile("fromFile.txt", "rw");
FileChannel      fromChannel = fromFile.getChannel();

RandomAccessFile toFile = new RandomAccessFile("toFile.txt", "rw");
FileChannel      toChannel = toFile.getChannel();

long position = 0;
long count = fromChannel.size();

fromChannel.transferTo(position, count, toChannel);12345678910
```

上面所说的关于SocketChannel的问题在transferTo()方法中同样存在。SocketChannel会一直传输数据直到目标buffer被填满。



# 十一Selector

Selector（选择器）是Java NIO中能够检测一到多个NIO通道，并能够知晓通道是否为诸如读写事件做好准备的组件。这样，一个单独的线程可以管理多个channel，从而管理多个网络连接。

#### **1为什么使用Selector：**

仅用单个线程来处理多个Channels的好处是，只需要更少的线程来处理通道。事实上，可以只用一个线程处理所有的通道。对于操作系统来说，线程之间上下文切换的开销很大，而且每个线程都要占用系统的一些资源（如内存）。因此，使用的线程越少越好。

但是，需要记住，现代的操作系统和CPU在多任务方面表现的越来越好，所以多线程的开销随着时间的推移，变得越来越小了。实际上，如果一个CPU有多个内核，不使用多任务可能是在浪费CPU能力。不管怎么说，关于那种设计的讨论应该放在另一篇不同的文章中。在这里，只要知道使用Selector能够处理多个通道就足够了。

#### **2Selector的创建：**

```java
Selector selector = Selector.open();

```

#### **3向Selector注册通道：**

为了将Channel和Selector配合使用，必须将channel注册到selector上。通过SelectableChannel.register()方法来实现，如下：

```java
channel.configureBlocking(false);
SelectionKey key = channel.register(selector, Selectionkey.OP_READ);

```

与Selector一起使用时，Channel必须处于非阻塞模式下。这意味着不能将FileChannel与Selector一起使用，因为FileChannel不能切换到非阻塞模式。而套接字通道都可以。

注意register()方法的第二个参数。这是一个“interest集合”，意思是在通过Selector监听Channel时对什么事件感兴趣。**可以监听四种不同类型的事件：**
**- Connect**
**- Accept**
**- Read**
**- Write**
通道触发了一个事件意思是该事件已经就绪。所以，某个channel成功连接到另一个服务器称为“连接就绪”。一个server socket channel准备好接收新进入的连接称为“接收就绪”。一个有数据可读的通道可以说是“读就绪”。等待写数据的通道可以说是“写就绪”。

**这四种事件用SelectionKey的四个常量来表示：**
**- SelectionKey.OP_CONNECT**
**- SelectionKey.OP_ACCEPT**
**- SelectionKey.OP_READ**
**- SelectionKey.OP_WRITE**

如果你对不止一种事件感兴趣，那么可以用“位或”操作符将常量连接起来，如下：

```java
int interestSet = SelectionKey.OP_READ | SelectionKey.OP_WRITE;

```

#### **4SelectionKey：**

当向Selector注册Channel时，register()方法会返回一个SelectionKey对象。这个对象包含了一些你感兴趣的属性：
**- interest集合**
**- ready集合**
**- Channel**
**- Selector**
**- 附加的对象（可选）**

**interest集合：**
interest集合是你所选择的感兴趣的事件集合。可以通过SelectionKey读写interest集合，像这样：

```java
int interestSet = selectionKey.interestOps();
boolean isInterestedInAccept  = (interestSet & SelectionKey.OP_ACCEPT) == SelectionKey.OP_ACCEPT;
boolean isInterestedInConnect = interestSet & SelectionKey.OP_CONNECT;
boolean isInterestedInRead    = interestSet & SelectionKey.OP_READ;
boolean isInterestedInWrite   = interestSet & SelectionKey.OP_WRITE;

```

可以看到，用“位与”操作interest 集合和给定的SelectionKey常量，可以确定某个确定的事件是否在interest 集合中。

**ready集合：**
ready 集合是通道已经准备就绪的操作的集合。在一次选择(Selection)之后，你会首先访问这个ready set。Selection将在下一小节进行解释。可以这样访问ready集合：

```java
int readySet = selectionKey.readyOps();
```

可以用像检测interest集合那样的方法，来检测channel中什么事件或操作已经就绪。但是，也可以使用以下四个方法，它们都会返回一个布尔类型：

```java
selectionKey.isAcceptable();
selectionKey.isConnectable();
selectionKey.isReadable();
selectionKey.isWritable();

```

**Channel + Selector：**
从SelectionKey访问Channel和Selector很简单。如下：

```java
Channel  channel  = selectionKey.channel();
Selector selector = selectionKey.selector();
```

**附加的对象：**
可以将一个对象或者更多信息附着到SelectionKey上，这样就能方便的识别某个给定的通道。例如，可以附加 与通道一起使用的Buffer，或是包含聚集数据的某个对象。使用方法如下：

```java
selectionKey.attach(theObject);
Object attachedObj = selectionKey.attachment();

```

还可以在用register()方法向Selector注册Channel的时候附加对象。如：

```java
SelectionKey key = channel.register(selector, SelectionKey.OP_READ, theObject);

```

#### **5通过Selector选择通道：**

**select()方法：**
一旦向Selector注册了一或多个通道，就可以调用几个重载的select()方法。这些方法返回你所感兴趣的事件（如连接、接受、读或写）已经准备就绪的那些通道。换句话说，如果你对“读就绪”的通道感兴趣，select()方法会返回读事件已经就绪的那些通道。

下面是select()方法：

- int select()
- int select(long timeout)
- int selectNow()

select()阻塞到至少有一个通道在你注册的事件上就绪了。

select(long timeout)和select()一样，除了最长会阻塞timeout毫秒(参数)。

selectNow()不会阻塞，不管什么通道就绪都立刻返回（译者注：此方法执行非阻塞的选择操作。如果自从前一次选择操作后，没有通道变成可选择的，则此方法直接返回零。）。

select()方法返回的int值表示有多少通道已经就绪。亦即，自上次调用select()方法后有多少通道变成就绪状态。如果调用select()方法，因为有一个通道变成就绪状态，返回了1，若再次调用select()方法，如果另一个通道就绪了，它会再次返回1。如果对第一个就绪的channel没有做任何操作，现在就有两个就绪的通道，但在每次select()方法调用之间，只有一个通道就绪了。

**selectedKeys()方法：**
一旦调用了select()方法，并且返回值表明有一个或更多个通道就绪了，然后可以通过调用selector的selectedKeys()方法，访问“已选择键集（selected key set）”中的就绪通道。如下所示：

```java
Set selectedKeys = selector.selectedKeys();

```

当像Selector注册Channel时，Channel.register()方法会返回一个SelectionKey 对象。这个对象代表了注册到该Selector的通道。可以通过SelectionKey的selectedKeySet()方法访问这些对象。
可以遍历这个已选择的键集合来访问就绪的通道。如下：

```java
Set selectedKeys = selector.selectedKeys();
Iterator keyIterator = selectedKeys.iterator();
while(keyIterator.hasNext()) {
    SelectionKey key = keyIterator.next();
    if(key.isAcceptable()) {
        // a connection was accepted by a ServerSocketChannel.
    } else if (key.isConnectable()) {
        // a connection was established with a remote server.
    } else if (key.isReadable()) {
        // a channel is ready for reading
    } else if (key.isWritable()) {
        // a channel is ready for writing
    }
    keyIterator.remove();
}
```

这个循环遍历已选择键集中的每个键，并检测各个键所对应的通道的就绪事件。

注意每次迭代末尾的keyIterator.remove()调用。Selector不会自己从已选择键集中移除SelectionKey实例。必须在处理完通道时自己移除。下次该通道变成就绪时，Selector会再次将其放入已选择键集中。

SelectionKey.channel()方法返回的通道需要转型成你要处理的类型，如ServerSocketChannel或SocketChannel等。

#### **6wakeUp()：**

某个线程调用select()方法后阻塞了，即使没有通道已经就绪，也有办法让其从select()方法返回。只要让其它线程在第一个线程调用select()方法的那个对象上调用Selector.wakeup()方法即可。阻塞在select()方法上的线程会立马返回。

如果有其它线程调用了wakeup()方法，但当前没有线程阻塞在select()方法上，下个调用select()方法的线程会立即“醒来（wake up）”。

#### **7close()：**

用完Selector后调用其close()方法会关闭该Selector，且使注册到该Selector上的所有SelectionKey实例无效。通道本身并不会关闭。

#### **8完整的示例：**

这里有一个完整的示例，打开一个Selector，注册一个通道注册到这个Selector上(通道的初始化过程略去),然后持续监控这个Selector的四种事件（接受，连接，读，写）是否就绪。

```java
Selector selector = Selector.open();
channel.configureBlocking(false);
SelectionKey key = channel.register(selector, SelectionKey.OP_READ);
while(true) {
  int readyChannels = selector.select();
  if(readyChannels == 0) continue;
  Set selectedKeys = selector.selectedKeys();
  Iterator keyIterator = selectedKeys.iterator();
  while(keyIterator.hasNext()) {
    SelectionKey key = keyIterator.next();
    if(key.isAcceptable()) {
        // a connection was accepted by a ServerSocketChannel.
    } else if (key.isConnectable()) {
        // a connection was established with a remote server.
    } else if (key.isReadable()) {
        // a channel is ready for reading
    } else if (key.isWritable()) {
        // a channel is ready for writing
    }
    keyIterator.remove();
  }
}
```



# 十二FileChannel

Java NIO中的FileChannel是一个连接到文件的通道。可以通过文件通道读写文件。

FileChannel无法设置为非阻塞模式，它总是运行在阻塞模式下。

#### **1打开FileChannel：**

在使用FileChannel之前，必须先打开它。但是，我们无法直接打开一个FileChannel，需要通过使用一个InputStream、OutputStream或RandomAccessFile来获取一个FileChannel实例。下面是通过RandomAccessFile打开FileChannel的示例：

```java
RandomAccessFile aFile = new RandomAccessFile("data/nio-data.txt", "rw");
FileChannel inChannel = aFile.getChannel();

```

#### **2从FileChannel读取数据：**

调用多个read()方法之一从FileChannel中读取数据。如：

```java
ByteBuffer buf = ByteBuffer.allocate(48);
int bytesRead = inChannel.read(buf);

```

首先，分配一个Buffer。从FileChannel中读取的数据将被读到Buffer中。

然后，调用FileChannel.read()方法。该方法将数据从FileChannel读取到Buffer中。read()方法返回的int值表示了有多少字节被读到了Buffer中。如果返回-1，表示到了文件末尾。

#### **3向FileChannel写数据：**

使用FileChannel.write()方法向FileChannel写数据，该方法的参数是一个Buffer。如：

```java
String newData = "New String to write to file..." + System.currentTimeMillis();
ByteBuffer buf = ByteBuffer.allocate(48);

buf.clear();
buf.put(newData.getBytes());

buf.flip();
while(buf.hasRemaining()) {
    channel.write(buf);
}
```

注意FileChannel.write()是在while循环中调用的。因为无法保证write()方法一次能向FileChannel写入多少字节，因此需要重复调用write()方法，直到Buffer中已经没有尚未写入通道的字节。

#### **4关闭FileChannel：**

用完FileChannel后必须将其关闭。如：

```java
channel.close();

```

#### **5FileChannel的position方法：**

有时可能需要在FileChannel的某个特定位置进行数据的读/写操作。可以通过调用position()方法获取FileChannel的当前位置。

也可以通过调用position(long pos)方法设置FileChannel的当前位置。

这里有两个例子:

```java
long pos = channel.position();
channel.position(pos +123);
```

如果将位置设置在文件结束符之后，然后试图从文件通道中读取数据，读方法将返回-1 —— 文件结束标志。

如果将位置设置在文件结束符之后，然后向通道中写数据，文件将撑大到当前位置并写入数据。这可能导致“文件空洞”，磁盘上物理文件中写入的数据间有空隙。

#### **6FileChannel的size方法：**

FileChannel实例的size()方法将返回该实例所关联文件的大小。如:

```java
long fileSize = channel.size();
```

#### **7FileChannel的truncate方法：**

可以使用FileChannel.truncate()方法截取一个文件。截取文件时，文件将中指定长度后面的部分将被删除。如：

```java
channel.truncate(1024);
```

这个例子截取文件的前1024个字节。

#### **8FileChannel的force方法：**

FileChannel.force()方法将通道里尚未写入磁盘的数据强制写到磁盘上。出于性能方面的考虑，操作系统会将数据缓存在内存中，所以无法保证写入到FileChannel里的数据一定会即时写到磁盘上。要保证这一点，需要调用force()方法。

force()方法有一个boolean类型的参数，指明是否同时将文件元数据（权限信息等）写到磁盘上。

下面的例子同时将文件数据和元数据强制写到磁盘上：

```java
channel.force(true);
```



# 十三SocketChannel

Java NIO中的SocketChannel是一个连接到TCP网络套接字的通道。可以通过以下2种方式创建SocketChannel：

- 打开一个SocketChannel并连接到互联网上的某台服务器。
- 一个新连接到达ServerSocketChannel时，会创建一个SocketChannel。

#### **1打开 SocketChannel：**

下面是SocketChannel的打开方式：

```java
SocketChannel socketChannel = SocketChannel.open();
socketChannel.connect(new InetSocketAddress("http://jenkov.com", 80));
```

#### **2关闭 SocketChannel：**

当用完SocketChannel之后调用SocketChannel.close()关闭SocketChannel：

```java
socketChannel.close();
```

#### **3从 SocketChannel 读取数据：**

要从SocketChannel中读取数据，调用一个read()的方法之一。以下是例子：

```java
ByteBuffer buf = ByteBuffer.allocate(48);
int bytesRead = socketChannel.read(buf);
```

首先，分配一个Buffer。从SocketChannel读取到的数据将会放到这个Buffer中。

然后，调用SocketChannel.read()。该方法将数据从SocketChannel 读到Buffer中。read()方法返回的int值表示读了多少字节进Buffer里。如果返回的是-1，表示已经读到了流的末尾（连接关闭了）。

#### **4写入 SocketChannel：**

写数据到SocketChannel用的是SocketChannel.write()方法，该方法以一个Buffer作为参数。示例如下：

```java
String newData = "New String to write to file..." + System.currentTimeMillis();
ByteBuffer buf = ByteBuffer.allocate(48);
buf.clear();
buf.put(newData.getBytes());
buf.flip();
while(buf.hasRemaining()) {
    channel.write(buf);
}
```

注意SocketChannel.write()方法的调用是在一个while循环中的。Write()方法无法保证能写多少字节到SocketChannel。所以，我们重复调用write()直到Buffer没有要写的字节为止。

#### **5非阻塞模式：**

可以设置 SocketChannel 为非阻塞模式（non-blocking mode）.设置之后，就可以在异步模式下调用connect(), read() 和write()了。

**connect()：**

如果SocketChannel在非阻塞模式下，此时调用connect()，该方法可能在连接建立之前就返回了。为了确定连接是否建立，可以调用finishConnect()的方法。像这样：

```java
socketChannel.configureBlocking(false);
socketChannel.connect(new InetSocketAddress("http://jenkov.com", 80));

while(! socketChannel.finishConnect() ){
    //wait, or do something else...
}
```

**write()：**
非阻塞模式下，write()方法在尚未写出任何内容时可能就返回了。所以需要在循环中调用write()。前面已经有例子了，这里就不赘述了。

**read()：**

非阻塞模式下,read()方法在尚未读取到任何数据时可能就返回了。所以需要关注它的int返回值，它会告诉你读取了多少字节。

#### **6非阻塞模式与选择器：**

非阻塞模式与选择器搭配会工作的更好，通过将一或多个SocketChannel注册到Selector，可以询问选择器哪个通道已经准备好了读取，写入等。Selector与SocketChannel的搭配使用会在后面详讲。



# 十四ServerSocketChannel

Java NIO中的 ServerSocketChannel 是一个可以监听新进来的TCP连接的通道, 就像标准IO中的ServerSocket一样。ServerSocketChannel类在 java.nio.channels包中。

例子：

```java
ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
serverSocketChannel.socket().bind(new InetSocketAddress(9999))；

while(true){
    SocketChannel socketChannel =
            serverSocketChannel.accept();
    //do something with socketChannel...
}
```

#### **1打开 ServerSocketChannel：**

通过调用 ServerSocketChannel.open() 方法来打开ServerSocketChannel.如：

```java
ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
```

#### **2关闭 ServerSocketChannel：**

通过调用ServerSocketChannel.close() 方法来关闭ServerSocketChannel. 如：

```java
serverSocketChannel.close();
```

#### **3监听新进来的连接：**

通过 ServerSocketChannel.accept() 方法监听新进来的连接。当 accept()方法返回的时候,它返回一个包含新进来的连接的 SocketChannel。因此, accept()方法会一直阻塞到有新连接到达。

通常不会仅仅只监听一个连接,在while循环中调用 accept()方法. 如下面的例子：

```java
while(true){
    SocketChannel socketChannel {
            serverSocketChannel.accept();
    //do something with socketChannel...
}
```

当然,也可以在while循环中使用除了true以外的其它退出准则。

#### **4非阻塞模式：**

ServerSocketChannel可以设置成非阻塞模式。在非阻塞模式下，accept() 方法会立刻返回，如果还没有新进来的连接,返回的将是null。 因此，需要检查返回的SocketChannel是否是null.如：

```java
ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
serverSocketChannel.socket().bind(new InetSocketAddress(9999));
serverSocketChannel.configureBlocking(false);

while(true){
    SocketChannel socketChannel =
            serverSocketChannel.accept();
    if(socketChannel != null){
        //do something with socketChannel...
    }
}
```



# 十五DatagramChannel

Java NIO中的DatagramChannel是一个能收发UDP包的通道。因为UDP是无连接的网络协议，所以不能像其它通道那样读取和写入。它发送和接收的是数据包。

#### **1打开 DatagramChannel：**

下面是 DatagramChannel 的打开方式：

```java
DatagramChannel channel = DatagramChannel.open();
channel.socket().bind(new InetSocketAddress(9999));
```

这个例子打开的 DatagramChannel可以在UDP端口9999上接收数据包。

#### **2接收数据：**

通过receive()方法从DatagramChannel接收数据，如：

```java
ByteBuffer buf = ByteBuffer.allocate(48);
buf.clear();
channel.receive(buf);
```

receive()方法会将接收到的数据包内容复制到指定的Buffer. 如果Buffer容不下收到的数据，多出的数据将被丢弃。

#### **3发送数据：**

通过send()方法从DatagramChannel发送数据，如:

```java
String newData = "New String to write to file..." + System.currentTimeMillis();
ByteBuffer buf = ByteBuffer.allocate(48);
buf.clear();
buf.put(newData.getBytes());
buf.flip();
int bytesSent = channel.send(buf, new InetSocketAddress("jenkov.com", 80));
```

这个例子发送一串字符到”jenkov.com”服务器的UDP端口80。 因为服务端并没有监控这个端口，所以什么也不会发生。也不会通知你发出的数据包是否已收到，因为UDP在数据传送方面没有任何保证。

#### **4连接到特定的地址：**

可以将DatagramChannel“连接”到网络中的特定地址的。由于UDP是无连接的，连接到特定地址并不会像TCP通道那样创建一个真正的连接。而是锁住DatagramChannel ，让其只能从特定地址收发数据。

#### **5例子:：**

```java
channel.connect(new InetSocketAddress("jenkov.com", 80));
```

当连接后，也可以使用read()和write()方法，就像在用传统的通道一样。只是在数据传送方面没有任何保证：

```java
int bytesRead = channel.read(buf);
int bytesWritten = channel.write(but);
```



# 十六DatagramChannel

Java NIO 管道是2个线程之间的单向数据连接。Pipe有一个source通道和一个sink通道。数据会被写到sink通道，从source通道读取。

这里是Pipe原理的图示：
![这里写图片描述](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/JavaIO%26NIO/9.png)

#### **1创建管道：**

通过Pipe.open()方法打开管道。例如：

```java
Pipe pipe = Pipe.open();
```

#### **2向管道写数据：**

要向管道写数据，需要访问sink通道。像这样：

```java
Pipe.SinkChannel sinkChannel = pipe.sink();
```

通过调用SinkChannel的write()方法，将数据写入SinkChannel,像这样：

```java
String newData = "New String to write to file..." + System.currentTimeMillis();
ByteBuffer buf = ByteBuffer.allocate(48);
buf.clear();
buf.put(newData.getBytes());
buf.flip();

while(buf.hasRemaining()) {
    sinkChannel.write(buf);
}
```

#### **3从管道读取数据：**

从读取管道的数据，需要访问source通道，像这样：

```java
Pipe.SourceChannel sourceChannel = pipe.source();
```

调用source通道的read()方法来读取数据，像这样：

```java
ByteBuffer buf = ByteBuffer.allocate(48);
int bytesRead = sourceChannel.read(buf);
```

read()方法返回的int值会告诉我们多少字节被读进了缓冲区。



# 十七NIO与IO

Java NIO和IO的主要区别：

```java
IO                NIO
面向流            面向缓冲
阻塞IO            非阻塞IO
无                选择器
```



# 十八参考资料

 https://blog.csdn.net/Somhu/article/details/78663755 

 https://blog.csdn.net/pengjunlee/article/details/65960533?depth_1-utm_source=distribute.pc_relevant.none-task-blog-OPENSEARCH-7&utm_source=distribute.pc_relevant.none-task-blog-OPENSEARCH-7 
