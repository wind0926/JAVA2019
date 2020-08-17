

/**
 * test
 */
public class TestMp {
    private ApplicationContext iocContext = new ClassPathXmlApplicationContext("applicationContext.xml");

    private EmployeeMapper employeeMapper=iocContext.getBean("employeeMapper",EmployeeMapper.class);



    /*
     * 通用 查询操作
     * */
    @Test
    public void testCommonSelect(){

        //1.通过id查询
        /*Employee employee = employeeMapper.selectById(7);
        System.out.println(employee);*/

        //2.通过多个列进行查询id+lastName,只能查询一条数据
/*        Employee employee = new Employee();
        employee.setId(7);
        employee.setLastName("MybatisPlus");

        Employee result = employeeMapper.selectOne(employee);
        System.out.println(result);*/

        //3.通过多个id查询
          /*      ArrayList<Integer> idList = new ArrayList<>();
        idList.add(1);
        idList.add(2);
        idList.add(3);
        idList.add(4);

        List<Employee> emps = employeeMapper.selectBatchIds(idList);
        System.out.println(emps);*/


        //4.通过map封装条件查询
       /* Map<String, Object> columnMap = new HashMap<>();
        //写数据库字段名
        columnMap.put("last_name","Tom");
        columnMap.put("gender",1);

        List<Employee> emps = employeeMapper.selectByMap(columnMap);
        System.out.println(emps);*/


        //5.分页查询,没有通过limit分页
      /*  List<Employee> emps = employeeMapper.selectPage(new Page<>(2, 2), null);
        System.out.println(emps);*/
/*
* //根据条件查询权属数据
* selectList
* public List<SysUser> searchUserList(User user) {
        EntityWrapper<User> ew = new EntityWrapper<>();
        ew.where(user.getId() != null, "id={0}", user.getId())
                .like(!Strings.isNullOrEmpty(user.getUserName()), "user_name", "%" + user.getUserName() + "%")
                .where(user.getStatus() != null, "status={0}", user.getStatus())；
        return userMapper.selectList(ew);
 }
* */
    }

    /*
     * 条件构造器 查询操作
     *
     * */
    @Test
    public void testEntityWrapperSelect(){
        //********使用的是数据库的字段
        //我们需要分页tb1_employee表中，年龄在18-50之间性别为男且姓名为Tom的所有用户
   /*     List<Employee> emps = employeeMapper.selectPage(new Page<Employee>(1, 2),
                new EntityWrapper<Employee>()
                        .between("age", 18, 50)
                        .eq("gender", 1)
                        .eq("last_name", "Tom")
        );
        System.out.println(emps);*/

        //查询tb1_employee表中，性别为女并且名字中带有“老师”或者邮箱中带有“a”
        List<Employee> emps = employeeMapper.selectList(
                new EntityWrapper<Employee>()
                        .eq("gender", 0)
                        .like("last_name", "老师")
//                        .or()   // gender = ? AND last_name LIKE ? OR eamil LIKE ?
                        .orNew()  //(gender = ? AND last_name LIKE ?) OR (eamil LIKE ?)
                        .like("email", "a")
        );
        System.out.println(emps);

    }

    @Test
    public void EntityWrapper(){
        //查询性别为女，根据age进行排序（asc/desc）,简单分页
        List<Employee> emps = employeeMapper.selectList(
                new EntityWrapper<Employee>()
                        .eq("gender", 0)
//                        .orderBy("age")
                        .orderDesc(Arrays.asList(new String [] {"age"}))
        );
        System.out.println(emps);
    }


    /*
    *通用 插入操作
    * */
    @Test
    public void testCommonInsert(){
//        初始化Employee对象
        Employee employee = new Employee();
        employee.setLastName("MP");
        employee.setEmail("mp@tuhu.cn");
//        employee.setGender(1);
        employee.setAge(22);
        //插入到数据库,根据传递进来实体类对象，做非空判断，只插入有数据的字段
//        Integer result = employeeMapper.insert(employee);

        //插入所有数据，不管属性是否为空，属性对应的字段都会出现在SQL语句中。
        Integer result = employeeMapper.insertAllColumn(employee);

        System.out.println(result);

        //获取当前数据在数据库的主键值
        Integer key = employee.getId();
        System.out.println("key:"+key);
    }

    /*
     *
     * 条件构造器 修改操作
     * */
    @Test
    public void testEntityWrapperUpdate(){
        Employee employee = new Employee();
        employee.setLastName("唐立磊");
        employee.setEmail("tanglilei@ytasc.cn");
        employee.setGender(0);
        /*
         * UPDATE employee SET lastName=? and  email=? and gender=? WHERE (last_name = ? AND age = ?)
         * */
        employeeMapper.update(employee,
                new EntityWrapper<Employee>()
                        .eq("last_name","Tom")
                        .eq("age",22)

        );
    }


    /*
    * 通用 更新操作
    * */
    @Test
    public void testCommonUpdate(){
        //初始化修改对象
        Employee employee = new Employee();
        employee.setId(7);
        employee.setLastName("MybatisPlus");
        employee.setAge(120);
        //在操作是，对对应的属性做非空判断
//        Integer result = employeeMapper.updateById(employee);

        //对所有的字段进行修改
        Integer result = employeeMapper.updateAllColumnById(employee);
        System.out.println(result);

    }




    /*
    * 通用 删除操作
    *
    * */
    @Test
    public void testCommonDelete(){

        //1.根据id进行删除
        /*Integer result = employeeMapper.deleteById(7);
        System.out.println(result);*/

        //2.根据条件进行删除
      /*  HashMap<String, Object> columnMap = new HashMap<>();
        columnMap.put("last_name","MP");
        columnMap.put("email","mp@.cn");

        Integer result = employeeMapper.deleteByMap(columnMap);
        System.out.println(result);*/

        //3.批量删除
        ArrayList<Integer> idList = new ArrayList<Integer>();
        idList.add(3);
        idList.add(4);
        idList.add(5);
        Integer result = employeeMapper.deleteBatchIds(idList);
        System.out.println(result);
    }


    /*
    * 条件构造器，删除操作
    *
    * */
    @Test
    public void testEntityWrapperDelete(Employee employee){
        employeeMapper.delete(new EntityWrapper<Employee>()
            .eq("last_name","dw")
            .eq("age",22)

/*
*w.where("deleted={0}", 1);这个deleted={0}是什么意思

占位符的意思，后面的参数与占位符对应 ew.where("deleted={0}", 1);
* 等价于 where deleted=1， 还可以n个占位符 比如 ew.where("deleted={0} or deleted={2}", 1,2,3);
*  等价于 where deleted=1 or deleted=3
*
*
* */
             .where("eamil={0}",employee.getEmail())

        );
    }



/*    @Test
    public void Condition(){
        employeeMapper.selectPage(
                new Page<Employee>(1,2),
                Condition.create
                );
    }*/


    @Test
    public void context() throws SQLException {

        DataSource ds = iocContext.getBean("dataSource", DataSource.class);
        Connection conn = ds.getConnection();
        System.out.println(conn);

    }





}
