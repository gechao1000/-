### nexus是一个强大的maven仓库管理器

> https://www.sonatype.com/download-nexus-repo-oss

#### 上传 jar 包到私服上

1. settings.xml

```xml
<server>
  <id>release</id>
  <username>admin</username>
  <password>admin</password>
</server>
<server>
  <id>snapshots</id>
  <username>admin</username>
  <password>admin</password>
</server>
```

2. pom.xml

```xml
<distributionManagement>
	<repository>
		<id>releases</id>
		<url>http://192.168.80.128/repository/maven-releases/</url>
	</repository>
	<snapshotRepository>
		<id>snapshots</id>
		<url>http://192.168.80.128/repository/maven-snapshots/</url>
	</snapshotRepository>
</distributionManagement>
```

3. 执行命令发布项目到私服（上传）` deploy`

------

#### 下载 jar 包到本地仓库

1. settings.xml  配置模板

```xml
<profile>
	<id>dev</id>
	<repositories>
		<repository>
			<id>nexus</id>
			<url>http://192.168.80.128/repository/maven-public/</url>
			<releases>
				<enabled>true</enabled>
			</releases>
			<snapshots>
				<enabled>true</enabled>
			</snapshots>
		</repository>
	</repositories>
	<pluginRepositories>
		<pluginRepository>
			<id>public</id>
			<name>Public Repositories</name>
			<url>http://192.168.80.128/repository/maven-public/</url>
		</pluginRepository>
	</pluginRepositories>
</profile>
```

2. settings.xml  激活模板

```xml
<activeProfiles>
	<activeProfile>dev</activeProfile>
</activeProfiles>
```

------

#### 安装第三方 jar 包

1. 到本地仓库

```shell
mvn install:install-file  -DgroupId=com.oracle  -DartifactId=ojdbc6 -Dversion=11.2.0.4 -Dpackaging=jar  -Dfile=D:/ojdbc6.jar
```

2. 到私服

```shell
mvn deploy:deploy-file  -DgroupId=com.oracle  -DartifactId=ojdbc6  -Dversion=11.2.0.4 -Dpackaging=jar  -Dfile=D:/ojdbc6.jar  -DrepositoryId=releases -Durl=http://192.168.80.128/repository/maven-releases/
```

