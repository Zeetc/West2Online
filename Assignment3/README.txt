使用前确认一下src目录下的druid.properties的url
并且更改一下mysql的用户名和密码
username=root
password=root
如果使用的mysql接口没更改，则无需更改url

否则把url的端口改一下      ↓
jdbc:mysql://localhost:3306/covid19info?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC


main方法在Test下的main里，运行即可

