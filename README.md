**MAKE BOARD WITH SPRING**
==========================

----------

**Development environment**
---------------------------

 - Window 10 pro 64bit
 - VirtualBox 5.2.0
 - ubuntu 16.04.3
 - Eclipse IDE for Java EE Developers (Oxygen.2 Release 4.7.2)
 - Tomcat 7.0.85
 - Bootstrap 4.0.0

----------
**Library**
-----------

 - junit 4.12
 - mysql connector java 6.0.5 mybatis 3.4.1
 - mybatis spring 1.3.0
 - spring jdbc 4.3.5
 - spring test 4.3.5
 - log4jdbc log4j2 jdbc4 1.16 (for using mybatis log)
 - jackson databind 2.8.6 (for using Json)

----------

**About this board**
--------------------

이 프로젝트는 스프링을 사용한 게시판 만들기 프로젝트입니다. 댓글 기능이 가능한 게시판이지만, 댓글의 페이징 기능은 구현되지 않았습니다. 언어 설정은 UTF-8로 설정되어 있습니다.


Make table in DataBase by MySQL Query
--------------------

테이블 생성시, MySQL Query가 필요하면 아래 Query문을 사용하세요.

 - 게시판 테이블

> CREATE TABLE GONGJI( 

> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ID INT NOT NULL PRIMARY KEY AUTO_INCREMENT,  // 게시물 번호

> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;TITLE VARCHAR(70), // 게시물 제목

> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;DATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP, // 게시물 작성날짜

> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;CONTENT TEXT, // 게시물 내용

> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;VIEWCNT INT)  // 게시물 조회수

> DEFAULT CHARSET=utf8;

 - 댓글 테이블

>  CREATE TABLE GONGJI_COMMENT(

>  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;RNO INT NOT NULL PRIMARY KEY AUTO_INCREMENT, // 댓글 번호

>  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ID INT NOT NULL, // 게시물 번호

>  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;REPLYTEXT TEXT NOT NULL, // 댓글 내용

>  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;REPLYER VARCHAR(20) NOT NULL, // 댓글 작성자

>  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;REGDATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP) // 댓글 작성날짜

> DEFAULT CHARSET=utf8;


댓글 테이블과 게시판 테이블의 게시물 번호는 서로 동일한 값을 가집니다. 이 프로젝트에서는 foreign key를 설정하지 않았지만, 원한다면 foreign key를 설정해서 사용할 수 있습니다. 만약 그럴경우, 게시물 삭제시에 댓글 테이블에 있는 해당 게시물의 댓글 데이터도 CASCADE를 이용해서 지워줘야 합니다.
