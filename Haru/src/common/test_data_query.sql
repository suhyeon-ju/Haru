INSERT INTO tb_member VALUES('admin','admin','ADMIN','ADMIN','2020/01/01','M','a@a.com','010-0000-0000','AD',null,null,null,null,null,null);
INSERT INTO tb_member VALUES('id1','password','NAME','NICK1','2020/01/01','F','a@a.com','010-0000-1234','Y','IMGURL','MESSAGE','OPENCHAT','CARRER','IDCARDURL','LICENSEURL');
INSERT INTO tb_member VALUES('id2','password','NAME','NICK2','2020/01/01','M','b@a.com','010-0000-2345','Y','IMGURL','MESSAGE','OPENCHAT','CARRER','IDCARDURL','LICENSEURL');
INSERT INTO tb_member VALUES('id3','password','NAME','NICK3','2020/01/01','F','c@a.com','010-0000-3456','N',null,null,null,null,null,null);
INSERT INTO tb_member VALUES('id4','password','NAME','닉네임','2020/01/01','M','d@a.com','010-0000-4567','N',null,null,null,null,null,null);
INSERT INTO tb_member VALUES('id5','password','NAME','자바','2020/01/01','F','e@a.com','010-0000-5678','N',null,null,null,null,null,null);
INSERT INTO tb_member VALUES('id6','password','NAME','오라클','2020/01/01','M','f@a.com','010-0000-6789','N',null,null,null,null,null,null);
INSERT INTO tb_member VALUES('id7','password','NAME','NICK7','2020/01/01','F','g@a.com','010-0000-7890','Y','IMGURL','MESSAGE','OPENCHAT','CARRER','IDCARDURL','LICENSEURL');

SELECT * FROM (SELECT * FROM TB_CLASSINFO WHERE category LIKE 'lifeStyle%') WHERE ROWNUM <=12;
SELECT * FROM TB_CLASSINFO WHERE category LIKE 'lifeStyle%';

INSERT INTO tb_board VALUES(bbs_no_seq.NEXTVAL,'admin','공지사항1','내용  내용 내용','2020/01/01');
INSERT INTO tb_board VALUES(bbs_no_seq.NEXTVAL,'admin','공지사항2','내용 내용 내용','2020/02/01');
INSERT INTO tb_board VALUES(bbs_no_seq.NEXTVAL,'admin','공지사항3','내용 내용 내용','2020/03/01');
INSERT INTO tb_board VALUES(bbs_no_seq.NEXTVAL,'admin','공지사항4','내용 내용 내용','2020/03/01');
INSERT INTO tb_board VALUES(bbs_no_seq.NEXTVAL,'admin','공지사항5','내용 내용 내용','2020/03/01');
INSERT INTO tb_board VALUES(bbs_no_seq.NEXTVAL,'admin','공지사항6','내용 내용 내용','2020/03/01');
INSERT INTO tb_board VALUES(bbs_no_seq.NEXTVAL,'admin','공지사항7','내용 내용 내용','2020/03/01');
INSERT INTO tb_board VALUES(bbs_no_seq.NEXTVAL,'admin','공지사항8','내용 내용 내용','2020/03/01');
INSERT INTO tb_board VALUES(bbs_no_seq.NEXTVAL,'admin','공지사항9','내용 내용 내용','2020/03/01');
INSERT INTO tb_board VALUES(bbs_no_seq.NEXTVAL,'admin','공지사항10','내용 내용 내용','2020/03/01');
INSERT INTO tb_board VALUES(bbs_no_seq.NEXTVAL,'admin','공지사항11','내용 내용 내용','2020/03/01');
INSERT INTO tb_board VALUES(bbs_no_seq.NEXTVAL,'admin','공지사항12','내용 내용 내용','2020/03/01');
INSERT INTO tb_board VALUES(bbs_no_seq.NEXTVAL,'admin','공지사항13','내용 내용 내용','2020/03/01');
INSERT INTO tb_board VALUES(bbs_no_seq.NEXTVAL,'admin','공지사항14','내용 내용 내용','2020/03/01');
INSERT INTO tb_board VALUES(bbs_no_seq.NEXTVAL,'admin','공지사항15','내용 내용 내용','2020/03/01');

INSERT INTO tb_classinfo VALUES(classpk_seq.NEXTVAL,'id1','클래스A 쿠킹','내용',100,5,'1 서울특별시 강남구 테헤란로 14길 6 (역삼동)남도빌딩 3층','popular','group','#요리 #쿠키',DEFAULT,DEFAULT,'pubImg',null);
INSERT INTO tb_classinfo VALUES(classpk_seq.NEXTVAL,'id1','클래스B 자바','내용',100,5,'1 서울특별시 강남구 테헤란로 14길 6 (역삼동)남도빌딩 3층','lifeStyle','group','#자바 #웹',DEFAULT,DEFAULT,'pubImg',null);
INSERT INTO tb_classinfo VALUES(classpk_seq.NEXTVAL,'id2','클래스C C#(Unity3D)','내용',100,5,'1 서울특별시 강남구 테헤란로 14길 6 (역삼동)남도빌딩 3층','COOK','group','#게임',DEFAULT,DEFAULT,'pubImg',null);
INSERT INTO tb_classinfo VALUES(classpk_seq.NEXTVAL,'id2','클래스D SQL','내용',100,5,'1 서울특별시 강남구 테헤란로 14길 6 (역삼동)남도빌딩 3층','COOK','group','#쿼리 #오라클',DEFAULT,DEFAULT,'pubImg',null);
INSERT INTO tb_classinfo VALUES(classpk_seq.NEXTVAL,'id7','클래스E (android) Kotlin&Java','내용',100,5,'1 서울특별시 강남구 테헤란로 14길 6 (역삼동)남도빌딩 3층','COOK','group','#안드로이드 #자바 #코틀린',DEFAULT,DEFAULT,'pubImg',null);
INSERT INTO tb_classinfo VALUES(classpk_seq.NEXTVAL,'id7','클래스F SPRING','내용',100,5,'1 서울특별시 강남구 테헤란로 14길 6 (역삼동)남도빌딩 3층','COOK','group','#스프링프레임워크 #웹',DEFAULT,DEFAULT,'pubImg',null);

INSERT INTO tb_classdate VALUES (classdate_seq.NEXTVAL,1,'2020/01/20',TO_DATE('10:00','hh24:mi'),TO_DATE('11:00','hh24:mi'));
INSERT INTO tb_classdate VALUES (classdate_seq.NEXTVAL,1,'2020/05/25',TO_DATE('12:30','hh24:mi'),TO_DATE('15:00','hh24:mi'));
INSERT INTO tb_classdate VALUES (classdate_seq.NEXTVAL,1,'2020/06/01',TO_DATE('10:00','hh24:mi'),TO_DATE('11:00','hh24:mi'));
INSERT INTO tb_classdate VALUES (classdate_seq.NEXTVAL,1,'2020/06/06',TO_DATE('12:30','hh24:mi'),TO_DATE('15:00','hh24:mi'));

INSERT INTO tb_classdate VALUES (classdate_seq.NEXTVAL,2,'2020/01/20',TO_DATE('10:00','hh24:mi'),TO_DATE('11:00','hh24:mi'));
INSERT INTO tb_classdate VALUES (classdate_seq.NEXTVAL,2,'2020/05/25',TO_DATE('12:30','hh24:mi'),TO_DATE('15:00','hh24:mi'));
INSERT INTO tb_classdate VALUES (classdate_seq.NEXTVAL,2,'2020/06/01',TO_DATE('10:00','hh24:mi'),TO_DATE('11:00','hh24:mi'));
INSERT INTO tb_classdate VALUES (classdate_seq.NEXTVAL,2,'2020/06/06',TO_DATE('12:30','hh24:mi'),TO_DATE('15:00','hh24:mi'));

INSERT INTO tb_classdate VALUES (classdate_seq.NEXTVAL,3,'2020/01/20',TO_DATE('10:00','hh24:mi'),TO_DATE('11:00','hh24:mi'));
INSERT INTO tb_classdate VALUES (classdate_seq.NEXTVAL,3,'2020/05/25',TO_DATE('12:30','hh24:mi'),TO_DATE('15:00','hh24:mi'));
INSERT INTO tb_classdate VALUES (classdate_seq.NEXTVAL,3,'2020/06/01',TO_DATE('10:00','hh24:mi'),TO_DATE('11:00','hh24:mi'));
INSERT INTO tb_classdate VALUES (classdate_seq.NEXTVAL,3,'2020/06/06',TO_DATE('12:30','hh24:mi'),TO_DATE('15:00','hh24:mi'));

INSERT INTO tb_classdate VALUES (classdate_seq.NEXTVAL,4,'2020/01/20',TO_DATE('10:00','hh24:mi'),TO_DATE('11:00','hh24:mi'));
INSERT INTO tb_classdate VALUES (classdate_seq.NEXTVAL,4,'2020/05/25',TO_DATE('12:30','hh24:mi'),TO_DATE('15:00','hh24:mi'));
INSERT INTO tb_classdate VALUES (classdate_seq.NEXTVAL,4,'2020/06/01',TO_DATE('10:00','hh24:mi'),TO_DATE('11:00','hh24:mi'));
INSERT INTO tb_classdate VALUES (classdate_seq.NEXTVAL,4,'2020/06/06',TO_DATE('12:30','hh24:mi'),TO_DATE('15:00','hh24:mi'));

INSERT INTO tb_classdate VALUES (classdate_seq.NEXTVAL,5,'2020/01/20',TO_DATE('10:00','hh24:mi'),TO_DATE('11:00','hh24:mi'));
INSERT INTO tb_classdate VALUES (classdate_seq.NEXTVAL,5,'2020/05/25',TO_DATE('12:30','hh24:mi'),TO_DATE('15:00','hh24:mi'));
INSERT INTO tb_classdate VALUES (classdate_seq.NEXTVAL,5,'2020/06/01',TO_DATE('10:00','hh24:mi'),TO_DATE('11:00','hh24:mi'));
INSERT INTO tb_classdate VALUES (classdate_seq.NEXTVAL,5,'2020/06/06',TO_DATE('12:30','hh24:mi'),TO_DATE('15:00','hh24:mi'));

INSERT INTO tb_classdate VALUES (classdate_seq.NEXTVAL,6,'2020/01/20',TO_DATE('10:00','hh24:mi'),TO_DATE('11:00','hh24:mi'));
INSERT INTO tb_classdate VALUES (classdate_seq.NEXTVAL,6,'2020/05/25',TO_DATE('12:30','hh24:mi'),TO_DATE('15:00','hh24:mi'));
INSERT INTO tb_classdate VALUES (classdate_seq.NEXTVAL,6,'2020/06/01',TO_DATE('10:00','hh24:mi'),TO_DATE('11:00','hh24:mi'));
INSERT INTO tb_classdate VALUES (classdate_seq.NEXTVAL,6,'2020/06/06',TO_DATE('12:30','hh24:mi'),TO_DATE('15:00','hh24:mi'));

INSERT INTO tb_class_image VALUES (class_image_seq.NEXTVAL,'1','이미지1','img1.jpg');
INSERT INTO tb_class_image VALUES (class_image_seq.NEXTVAL,'1','이미지2','img2.jpg');
INSERT INTO tb_class_image VALUES (class_image_seq.NEXTVAL,'1','이미지3','img3.jpg');
INSERT INTO tb_class_image VALUES (class_image_seq.NEXTVAL,'2','이미지1','img1.jpg');
INSERT INTO tb_class_image VALUES (class_image_seq.NEXTVAL,'2','이미지2','img2.jpg');
INSERT INTO tb_class_image VALUES (class_image_seq.NEXTVAL,'2','이미지3','img3.jpg');
INSERT INTO tb_class_image VALUES (class_image_seq.NEXTVAL,'3','이미지1','img1.jpg');
INSERT INTO tb_class_image VALUES (class_image_seq.NEXTVAL,'3','이미지2','img2.jpg');
INSERT INTO tb_class_image VALUES (class_image_seq.NEXTVAL,'3','이미지3','img3.jpg');
INSERT INTO tb_class_image VALUES (class_image_seq.NEXTVAL,'4','이미지1','img1.jpg');
INSERT INTO tb_class_image VALUES (class_image_seq.NEXTVAL,'4','이미지2','img2.jpg');
INSERT INTO tb_class_image VALUES (class_image_seq.NEXTVAL,'4','이미지3','img3.jpg');
INSERT INTO tb_class_image VALUES (class_image_seq.NEXTVAL,'5','이미지1','img1.jpg');
INSERT INTO tb_class_image VALUES (class_image_seq.NEXTVAL,'5','이미지2','img2.jpg');
INSERT INTO tb_class_image VALUES (class_image_seq.NEXTVAL,'5','이미지3','img3.jpg');
INSERT INTO tb_class_image VALUES (class_image_seq.NEXTVAL,'6','이미지1','img1.jpg');
INSERT INTO tb_class_image VALUES (class_image_seq.NEXTVAL,'6','이미지2','img2.jpg');
INSERT INTO tb_class_image VALUES (class_image_seq.NEXTVAL,'6','이미지3','img3.jpg');


INSERT INTO tb_class_member VALUES ('id3',1,'Y',DEFAULT);
INSERT INTO tb_class_member VALUES ('id4',1,'Y',DEFAULT);
INSERT INTO tb_class_member VALUES ('id5',1,'Y',DEFAULT);
INSERT INTO tb_class_member VALUES ('id6',1,'Y',DEFAULT);

INSERT INTO tb_class_member VALUES ('id3',2,'Y',DEFAULT);
INSERT INTO tb_class_member VALUES ('id4',2,'Y',DEFAULT);
INSERT INTO tb_class_member VALUES ('id5',2,'Y',DEFAULT);
INSERT INTO tb_class_member VALUES ('id6',2,'Y',DEFAULT);

INSERT INTO tb_class_member VALUES ('id3',3,'Y',DEFAULT);
INSERT INTO tb_class_member VALUES ('id4',3,'Y',DEFAULT);
INSERT INTO tb_class_member VALUES ('id5',3,'Y',DEFAULT);
INSERT INTO tb_class_member VALUES ('id6',3,'Y',DEFAULT);

INSERT INTO tb_class_member VALUES ('id3',4,'Y',DEFAULT);
INSERT INTO tb_class_member VALUES ('id4',4,'Y',DEFAULT);
INSERT INTO tb_class_member VALUES ('id5',4,'Y',DEFAULT);
INSERT INTO tb_class_member VALUES ('id6',4,'Y',DEFAULT);

INSERT INTO tb_class_member VALUES ('id3',5,'Y',DEFAULT);
INSERT INTO tb_class_member VALUES ('id4',5,'Y',DEFAULT);
INSERT INTO tb_class_member VALUES ('id5',5,'Y',DEFAULT);
INSERT INTO tb_class_member VALUES ('id6',5,'Y',DEFAULT);

INSERT INTO tb_class_member VALUES ('id3',6,'Y',DEFAULT);
INSERT INTO tb_class_member VALUES ('id4',6,'Y',DEFAULT);
INSERT INTO tb_class_member VALUES ('id5',6,'Y',DEFAULT);
INSERT INTO tb_class_member VALUES ('id6',6,'Y',DEFAULT);

INSERT INTO tb_pay VALUES(1,'id3',1,1,'pay_title','card',100,'Y',sysdate);
INSERT INTO tb_pay VALUES(2,'id3',1,2,'pay_title','card',100,'Y',sysdate);
INSERT INTO tb_pay VALUES(3,'id3',1,3,'pay_title','card',100,'Y',sysdate);
INSERT INTO tb_pay VALUES(4,'id3',1,4,'pay_title','card',100,'Y',sysdate);

INSERT INTO tb_pay VALUES(5,'id3',2,5,'pay_title','card',100,'Y',sysdate);
INSERT INTO tb_pay VALUES(6,'id3',2,6,'pay_title','card',100,'Y',sysdate);
INSERT INTO tb_pay VALUES(7,'id3',2,7,'pay_title','card',100,'Y',sysdate);
INSERT INTO tb_pay VALUES(8,'id3',2,8,'pay_title','card',100,'Y',sysdate);

INSERT INTO tb_pay VALUES(9,'id3',3,9,'pay_title','card',100,'Y',sysdate);
INSERT INTO tb_pay VALUES(10,'id3',3,10,'pay_title','card',100,'Y',sysdate);
INSERT INTO tb_pay VALUES(11,'id3',3,11,'pay_title','card',100,'Y',sysdate);
INSERT INTO tb_pay VALUES(12,'id3',3,12,'pay_title','card',100,'Y',sysdate);

INSERT INTO tb_pay VALUES(13,'id3',4,13,'pay_title','card',100,'Y',sysdate);
INSERT INTO tb_pay VALUES(14,'id3',4,14,'pay_title','card',100,'Y',sysdate);
INSERT INTO tb_pay VALUES(15,'id3',4,15,'pay_title','card',100,'Y',sysdate);
INSERT INTO tb_pay VALUES(16,'id3',4,16,'pay_title','card',100,'Y',sysdate);

INSERT INTO tb_pay VALUES(17,'id3',5,17,'pay_title','card',100,'Y',sysdate);
INSERT INTO tb_pay VALUES(18,'id3',5,18,'pay_title','card',100,'Y',sysdate);
INSERT INTO tb_pay VALUES(19,'id3',5,19,'pay_title','card',100,'Y',sysdate);
INSERT INTO tb_pay VALUES(20,'id3',5,20,'pay_title','card',100,'Y',sysdate);

INSERT INTO tb_pay VALUES(21,'id3',6,21,'pay_title','card',100,'Y',sysdate);
INSERT INTO tb_pay VALUES(22,'id3',6,22,'pay_title','card',100,'Y',sysdate);
INSERT INTO tb_pay VALUES(23,'id3',6,23,'pay_title','card',100,'Y',sysdate);
INSERT INTO tb_pay VALUES(24,'id3',6,24,'pay_title','card',100,'Y',sysdate);

