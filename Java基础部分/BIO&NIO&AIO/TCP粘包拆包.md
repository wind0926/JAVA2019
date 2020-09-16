## TCP粘包/拆包

什么是tcp粘包？拆包？

![img](file:///C:/Users/yuangong/Documents/WXWork/1688852923888674/Cache/Image/2020-07/Screenshot_20200721-160415(1).jpg)

答：c向s发送2个数据包，那么可能会出现3种情况：

1 正常，

2 两个包一同发送，

3 s接收到不完整的或多出一部分的数据包。    

  原因？

答：1 c一次发送的数据大于套接字缓冲区，拆包，

2 c一次发送数据小于套接字缓冲区大小，网卡将多次发送的数据一次发送到s, 

3 c不及时读取 

4 tcp报文长度大于分段长度。   

如何？答：c添加包首部，长度 ！固定每次发送的报文长度，不够补0.! 约定好包的边界，添加首部尾部标识

![image-20200721161527319](C:\Users\yuangong\AppData\Roaming\Typora\typora-user-images\image-20200721161527319.png)





https://segmentfault.com/a/1190000003063859?utm_source=Weibo&utm_medium=shareLink&utm_campaign=socialShare&from=timeline&isappinstalled=0

多路复用