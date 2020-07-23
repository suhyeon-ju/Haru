DROP SEQUENCE review_cmdpk;
DROP SEQUENCE class_image_seq;
DROP SEQUENCE classdate_seq;
DROP SEQUENCE classpk_seq;
DROP SEQUENCE bbs_no_seq;
-- 기존 시퀀스 삭제

DROP TABLE tb_class_review;
DROP TABLE tb_class_member;
DROP TABLE tb_pay;
DROP TABLE tb_class_image;
DROP TABLE tb_classdate;
DROP TABLE tb_classinfo;
DROP TABLE tb_board;
DROP TABLE tb_member;
DROP TABLE tb_sms;
-- 기존 테이블 삭제

CREATE TABLE tb_member(
        id VARCHAR2(100),
        pwd VARCHAR2(100) DEFAULT 'cGFzc3dvcmQ=' NOT NULL,
        name VARCHAR2(100) NOT NULL,
        nickname VARCHAR2(100) NOT NULL,
        birth DATE NOT NULL,
        gender VARCHAR2(3) NOT NULL,
        email VARCHAR2(100),
        phone VARCHAR2(100),
        isclass VARCHAR2(3) DEFAULT 'N' NOT NULL,
        profileImg VARCHAR2(100),
        pmessage VARCHAR2(100),
        openchat VARCHAR2(100),
        career VARCHAR2(100),
        idcard VARCHAR2(100),
        license VARCHAR2(100),
        CONSTRAINT PK_id_tb_member PRIMARY KEY (id),
        CONSTRAINT UK_nickname_tb_member UNIQUE (nickname),
        CONSTRAINT CHK_gender_tb_member CHECK (gender IN ('M','F')),
        CONSTRAINT CHK_email_tb_member CHECK (email LIKE '%@%'),
        CONSTRAINT CHK_isclass CHECK (isclass IN ('Y','N','AD')),
        CONSTRAINT CHK_phone_tb_member CHECK (phone LIKE '01_-___%-____')
);
-- 회원 정보 테이블 생성

CREATE TABLE tb_sms(
        phone VARCHAR2(100) NOT NULL,
        rankey NUMBER NOT NULL,
        CONSTRAINT CHK_phone_tb_sms CHECK (phone LIKE '01_-___%-____')
);
-- 문자 인증 테이블 생성

CREATE SEQUENCE bbs_no_seq NOCYCLE NOCACHE;
CREATE TABLE tb_board(
	bbs_no NUMBER,
	id VARCHAR2(100) NOT NULL,
	title VARCHAR2(100) NOT NULL,
	content VARCHAR2(100) NOT NULL,
	regDate DATE NOT NULL,
	CONSTRAINT PK_bbs_no_tb_board PRIMARY KEY (bbs_no),
	CONSTRAINT FK_id_tb_board FOREIGN KEY (id) REFERENCES tb_member(id) ON DELETE CASCADE
);
-- 게시판 테이블 생성+시퀀스

CREATE SEQUENCE classpk_seq NOCYCLE NOCACHE;
CREATE TABLE tb_classinfo(
	classpk NUMBER,
	id VARCHAR2(100) NOT NULL,
	title VARCHAR2(100) NOT NULL,
	content VARCHAR2(4000) NOT NULL,
	price NUMBER NOT NULL,
	allstudent NUMBER DEFAULT 30 NOT NULL,
	loc VARCHAR2(100) NOT NULL,
	category VARCHAR2(100) DEFAULT 'ALL' NOT NULL,
	classtype VARCHAR2(100) NOT NULL,
	keyword VARCHAR2(100),
	rank NUMBER DEFAULT 0 NOT NULL,
	rankcnt NUMBER DEFAULT 0 NOT NULL,
	pubImg VARCHAR2(100) NOT NULL,
	cellurl VARCHAR2(100),
	CONSTRAINT PK_classpk_tb_classinfo PRIMARY KEY (classpk),
	CONSTRAINT FK_id_tb_classinfo FOREIGN KEY (id) REFERENCES tb_member(id) ON DELETE CASCADE,
	CONSTRAINT CHK_price_tb_class CHECK (0 <= price),
	CONSTRAINT CHK_allstudent_tb_classinfo CHECK (0 < allstudent),
	CONSTRAINT CHK_rank_tb_classinfo CHECK (0 <= rank),
	CONSTRAINT CHK_rankcnt_tb_classinfo CHECK (0 <= rankcnt)
);
-- 클래스 정보 테이블 생성+시퀀스

CREATE SEQUENCE classdate_seq NOCYCLE NOCACHE;
CREATE TABLE tb_classdate(
	classdatepk NUMBER,
	classpk NUMBER NOT NULL,
	classday DATE NOT NULL,
	str_time DATE NOT NULL,
	end_time DATE NOT NULL,
	CONSTRAINT PK_classdatepk_tb_classdate PRIMARY KEY (classdatepk),
	CONSTRAINT FK_classpk_tb_cdate FOREIGN KEY (classpk) REFERENCES tb_classinfo(classpk) ON DELETE CASCADE,
	CONSTRAINT UK_tb_classdate UNIQUE (classpk,classday,str_time)
);
-- 클래스에 따른 수업일 정보 테이블

CREATE SEQUENCE class_image_seq NOCYCLE NOCACHE;
CREATE TABLE tb_class_image(
	banner_no number,
	classpk number NOT NULL,
	image_name VARCHAR2(100) NOT NULL,
	image_url VARCHAR2(100) NOT NULL,
	CONSTRAINT PK_banner_no_tb_class_image PRIMARY KEY (banner_no),
	CONSTRAINT FK_classpk_tb_class_image FOREIGN KEY (classpk) REFERENCES tb_classinfo(classpk) ON DELETE CASCADE
);
-- 클래스에 대한 이미지 리스트 테이블

CREATE TABLE tb_pay(
	pay_uid NUMBER,
	id VARCHAR2(100) NOT NULL,
	classpk NUMBER NOT NULL,
	classdatepk NUMBER NOT NULL,
	pay_name VARCHAR2(100) NOT NULL,
	pay_method VARCHAR2(100) DEFAULT 'none' NOT NULL,
	pay_price number NOT NULL,
	pay_true VARCHAR2(100) DEFAULT 'N' NOT NULL,
	pay_time DATE,
	CONSTRAINT PK_uid_tb_pay PRIMARY KEY (pay_uid),
	CONSTRAINT FK_id_tb_pay FOREIGN KEY (id) REFERENCES tb_member(id),
	CONSTRAINT FK_classpk_tb_pay FOREIGN KEY (classpk) REFERENCES tb_classinfo(classpk),
	CONSTRAINT FK_classdatepk_tb_pay FOREIGN KEY (classdatepk) REFERENCES tb_classdate(classdatepk),
	CONSTRAINT CHK_method_tb_pay CHECK (pay_method IN ('card','trans','vbank','phone','none')),
	CONSTRAINT CHK_price_tb_pay CHECK (0 <= pay_price),
	CONSTRAINT CHK_true_tb_pay CHECK (pay_true IN ('Y','N')),
	CONSTRAINT UK_tb_pay UNIQUE (classpk,classdatepk,pay_uid)
);
-- 결제 요청,기록용 테이블

CREATE TABLE tb_class_member(
	id VARCHAR2(100) NOT NULL,
	classpk NUMBER NOT NULL,
	chk_class VARCHAR(3) DEFAULT 'N' NOT NULL,
	chk_wish VARCHAR(3) DEFAULT 'N' NOT NULL,
	CONSTRAINT FK_id_tb_class_member FOREIGN KEY (id) REFERENCES tb_member(id) ON DELETE CASCADE,
	CONSTRAINT FK_classpk_tb_class_member FOREIGN KEY (classpk) REFERENCES tb_classinfo(classpk) ON DELETE CASCADE,
	CONSTRAINT CHK_class_tb_class_member CHECK (chk_class IN ('Y','N')),
	CONSTRAINT CHK_wish_tb_class_member CHECK (chk_wish IN ('Y','N')),
	CONSTRAINT UK_path_tb_class_member UNIQUE (id,classpk)
);
-- 회원과 클래스에 대한 일대일 연결 테이블 수강여부,위시여부 정보 테이블

CREATE SEQUENCE review_cmdpk NOCYCLE NOCACHE;
CREATE TABLE tb_class_review(
	cmdpk NUMBER,
	content VARCHAR2(1000) NOT NULL,
	strp NUMBER NOT NULL,
	id VARCHAR2(100) NOT NULL,
	classpk NUMBER NOT NULL,
	regdate DATE,
	CONSTRAINT PK_cmdpk_tb_class_review PRIMARY KEY (cmdpk),
	CONSTRAINT FK_id_tb_class_review FOREIGN KEY (id) REFERENCES tb_member(id) ON DELETE CASCADE,
	CONSTRAINT FK_classpk_tb_class_review FOREIGN KEY (classpk) REFERENCES tb_classinfo(classpk) ON DELETE CASCADE,
	CONSTRAINT CHK_strp_tb_class_review CHECK (0 <= strp)
);
-- 클래스에 대한 리뷰 정보 테이블
select * from tb_member;