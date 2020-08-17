# FastJson对于JSON格式字符串、JSON对象及JavaBean之间的相互转换

fastJson对于json格式字符串的解析主要用到了一下三个类：

JSON：fastJson的解析器，用于JSON格式字符串与JSON对象及javaBean之间的转换。

JSONObject：fastJson提供的json对象。

JSONArray：fastJson提供json数组对象。

我们可以把JSONObject当成一个Map<String,Object>来看，只是JSONObject提供了更为丰富便捷的方法，方便我们对于对象属性的操作。我们看一下源码。

![img](https://images2015.cnblogs.com/blog/1014108/201707/1014108-20170702123831164-204043901.png)

同样我们可以把JSONArray当做一个List<Object>，可以把JSONArray看成JSONObject对象的一个集合。

![img](https://images2015.cnblogs.com/blog/1014108/201707/1014108-20170702124211524-639489808.png)

此外，由于JSONObject和JSONArray继承了JSON，所以说也可以直接使用两者对JSON格式字符串与JSON对象及javaBean之间做转换，不过为了避免混淆我们还是使用JSON。

首先定义三个json格式的字符串，作为我们的数据源。

```json
//json字符串-简单对象型
private static final String  JSON_OBJ_STR = "{\"studentName\":\"lily\",\"studentAge\":12}";
//json字符串-数组类型
private static final String  JSON_ARRAY_STR = "[{\"studentName\":\"lily\",\"studentAge\":12},{\"studentName\":\"lucy\",\"studentAge\":15}]";
//复杂格式json字符串
private static final String  COMPLEX_JSON_STR = "{\"teacherName\":\"crystall\",\"teacherAge\":27,\"course\":{\"courseName\":\"english\",\"code\":1270},\"students\":[{\"studentName\":\"lily\",\"studentAge\":12},{\"studentName\":\"lucy\",\"studentAge\":15}]}";
```

示例1：JSON格式字符串与JSON对象之间的转换。

示例1.1-json字符串-简单对象型与JSONObject之间的转换

```java
/**
 * json字符串-简单对象型与JSONObject之间的转换
 */
public static void testJSONStrToJSONObject(){
 
    JSONObject jsonObject = JSON.parseObject(JSON_OBJ_STR);
    //JSONObject jsonObject1 = JSONObject.parseObject(JSON_OBJ_STR); //因为JSONObject继承了JSON，所以这样也是可以的
 
    System.out.println(jsonObject.getString("studentName")+":"+jsonObject.getInteger("studentAge"));
 
}
```
示例1.2-json字符串-数组类型与JSONArray之间的转换

```java
/**
 * json字符串-数组类型与JSONArray之间的转换
 */
public static void testJSONStrToJSONArray(){
 
    JSONArray jsonArray = JSON.parseArray(JSON_ARRAY_STR);
    //JSONArray jsonArray1 = JSONArray.parseArray(JSON_ARRAY_STR);//因为JSONArray继承了JSON，所以这样也是可以的
 
    //遍历方式1
    int size = jsonArray.size();
    for (int i = 0; i < size; i++){
        JSONObject jsonObject = jsonArray.getJSONObject(i);
        System.out.println(jsonObject.getString("studentName")+":"+jsonObject.getInteger("studentAge"));
    }
    //遍历方式2
    for (Object obj : jsonArray) {
        JSONObject jsonObject = (JSONObject) obj;
        System.out.println(jsonObject.getString("studentName")+":"+jsonObject.getInteger("studentAge"));
    }
}
```
示例1.3-复杂json格式字符串与JSONObject之间的转换

```java
/**
 * 复杂json格式字符串与JSONObject之间的转换
 */
public static void testComplexJSONStrToJSONObject(){
 
    JSONObject jsonObject = JSON.parseObject(COMPLEX_JSON_STR);
    //JSONObject jsonObject1 = JSONObject.parseObject(COMPLEX_JSON_STR);//因为JSONObject继承了JSON，所以这样也是可以的
    
    String teacherName = jsonObject.getString("teacherName");
    Integer teacherAge = jsonObject.getInteger("teacherAge");
    JSONObject course = jsonObject.getJSONObject("course");
    JSONArray students = jsonObject.getJSONArray("students");
 
}
```
示例2：JSON格式字符串与javaBean之间的转换。

首先，我们针对数据源所示的字符串，提供三个javaBean。

```java
public class Student {
private String studentName;
private Integer studentAge;
 
public String getStudentName() {
    return studentName;
}
 
public void setStudentName(String studentName) {
    this.studentName = studentName;
}
 
public Integer getStudentAge() {
    return studentAge;
}
 
public void setStudentAge(Integer studentAge) {
    this.studentAge = studentAge;
	}
}
```


```java
public class Course {
private String courseName;
private Integer code;
 
public String getCourseName() {
    return courseName;
}
 
public void setCourseName(String courseName) {
    this.courseName = courseName;
}
 
public Integer getCode() {
    return code;
}
 
public void setCode(Integer code) {
    this.code = code;
	}
}
```


```java
public class Teacher {
private String teacherName;
private Integer teacherAge;
private Course course;
private List<Student> students;
 
public String getTeacherName() {
    return teacherName;
}
 
public void setTeacherName(String teacherName) {
    this.teacherName = teacherName;
}
 
public Integer getTeacherAge() {
    return teacherAge;
}
 
public void setTeacherAge(Integer teacherAge) {
    this.teacherAge = teacherAge;
}
 
public Course getCourse() {
    return course;
}
 
public void setCourse(Course course) {
    this.course = course;
}
 
public List<Student> getStudents() {
    return students;
}
 
public void setStudents(List<Student> students) {
    this.students = students;
	}
}
```
json字符串与javaBean之间的转换推荐使用 **TypeReference<T>** 这个类，使用泛型可以更加清晰，当然也有其它的转换方式，这里就不做探讨了。

示例2.1-json字符串-简单对象型与javaBean之间的转换



```java
   /**
     * json字符串-简单对象与JavaBean_obj之间的转换
     */
    public static void testJSONStrToJavaBeanObj(){
 
        Student student = JSON.parseObject(JSON_OBJ_STR, new TypeReference<Student>() {});
        //Student student1 = JSONObject.parseObject(JSON_OBJ_STR, new TypeReference<Student>() {});//因为JSONObject继承了JSON，所以这样也是可以的
 
        System.out.println(student.getStudentName()+":"+student.getStudentAge());
 
    }
```


示例2.2-json字符串-数组类型与javaBean之间的转换

```java
/**
     * json字符串-数组类型与JavaBean_List之间的转换
     */
    public static void testJSONStrToJavaBeanList(){
        
        ArrayList<Student> students = JSON.parseObject(JSON_ARRAY_STR, new TypeReference<ArrayList<Student>>() {});
        //ArrayList<Student> students1 = JSONArray.parseObject(JSON_ARRAY_STR, new TypeReference<ArrayList<Student>>() {});//因为JSONArray继承了JSON，所以这样也是可以的
        
        for (Student student : students) {
            System.out.println(student.getStudentName()+":"+student.getStudentAge());
        }
    }
```


示例2.3-复杂json格式字符串与与javaBean之间的转换

```java
/**
 * 复杂json格式字符串与JavaBean_obj之间的转换
 */
public static void testComplexJSONStrToJavaBean(){
 
    Teacher teacher = JSON.parseObject(COMPLEX_JSON_STR, new TypeReference<Teacher>() {});
    //Teacher teacher1 = JSON.parseObject(COMPLEX_JSON_STR, new TypeReference<Teacher>() {});//因为JSONObject继承了JSON，所以这样也是可以的
    String teacherName = teacher.getTeacherName();
    Integer teacherAge = teacher.getTeacherAge();
    Course course = teacher.getCourse();
    List<Student> students = teacher.getStudents();
}
```


对于TypeReference<T>，由于其构造方法使用 protected 进行修饰，所以在其他包下创建其对象的时候，要用其实现类的子类：new TypeReference<Teacher>() {}

![img](https://images2015.cnblogs.com/blog/1014108/201707/1014108-20170702143136649-1332485400.png)

此外的：

1，对于JSON对象与JSON格式字符串的转换可以直接用 toJSONString()这个方法。

2，javaBean与JSON格式字符串之间的转换要用到：JSON.toJSONString(obj);

3，javaBean与json对象间的转换使用：JSON.toJSON(obj)，然后使用强制类型转换，JSONObject或者JSONArray。