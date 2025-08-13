
DROP TABLE IF EXISTS MY_TEAM_PLAYER;
DROP TABLE IF EXISTS PLAYER;
DROP TABLE IF EXISTS OTHER_TEAM;
DROP TABLE IF EXISTS MYTEAM;

-- MYTEAM 테이블
CREATE TABLE MYTEAM (
    TEAM_ID INT AUTO_INCREMENT NOT NULL PRIMARY KEY COMMENT '팀아이디',
    TEAM_NAME VARCHAR(100) NOT NULL COMMENT '팀이름',
    BUDGET INT NOT NULL COMMENT '예산',
    TACTICS VARCHAR(100) NOT NULL COMMENT '전술'
) COMMENT='내 팀';

-- OTHER_TEAM 테이블
CREATE TABLE OTHER_TEAM (
    OPPONENT_ID INT AUTO_INCREMENT NOT NULL PRIMARY KEY COMMENT '팀아이디',
    OPPONENT_NAME VARCHAR(100) NOT NULL COMMENT '팀 이름',
    OPPONENT_LEAGUE VARCHAR(100) NOT NULL COMMENT '팀 리그'
) COMMENT='상대 팀';

INSERT INTO OTHER_TEAM (OPPONENT_NAME, OPPONENT_LEAGUE) VALUES
('바르셀로나', '라리가'),
('맨체스터 시티', '프리미어리그'),
('바이에른 뮌헨', '분데스리가'),
( '유벤투스', '세리에 A'),
('파리 생제르맹', '리그앙');


-- PLAYER 테이블
CREATE TABLE PLAYER (
    PLAYER_ID INT AUTO_INCREMENT NOT NULL PRIMARY KEY COMMENT '선수아이디',
    PLAYER_NAME VARCHAR(100) NOT NULL COMMENT '선수이름',
    PLAYER_VALUE INT NOT NULL COMMENT '선수가치',
    PLAYER_POSITION VARCHAR(50) NOT NULL COMMENT '선수포지션',
    OPPONENT_ID INT NOT NULL COMMENT '팀아이디',
    PLAYER_CONTRIBUTION FLOAT NOT NULL COMMENT '팀기여도',
    FOREIGN KEY (OPPONENT_ID) REFERENCES OTHER_TEAM(OPPONENT_ID) ON DELETE CASCADE
) COMMENT='선수';

INSERT INTO player (PLAYER_NAME,PLAYER_VALUE,PLAYER_POSITION,OPPONENT_ID,PLAYER_CONTRIBUTION) VALUES 
-- Team 1 (바르사)
('로베르트 레반도프스키', 12000, 'FW', 1, 0.94),
('하피냐',                 8800,  'FW', 1, 0.86),
('라민 야말',             12500, 'FW', 1, 0.96),

('페드리',                11200, 'MF', 1, 0.93),
('프렌키 데 용',          10800, 'MF', 1, 0.90),
('가비',                   9000, 'MF', 1, 0.84),

('쥘 쿤데',                8200, 'DF', 1, 0.84),
('로날드 아라우호',        9800, 'DF', 1, 0.91),
('알레한드로 발데',        7600, 'DF', 1, 0.78),
('안드레아스 크리스텐센',   8000, 'DF', 1, 0.82),

('마르크안드레 테어슈테겐', 9200, 'GK', 1, 0.92),

-- Team 2 (맨시티)
('엘링 홀란드',          13000, 'FW', 2, 0.97),
('필 포든',              10500, 'FW', 2, 0.88),
('잭 그릴리쉬',            7800, 'FW', 2, 0.76),

('로드리',               12500, 'MF', 2, 0.96),
('베르나르두 실바',         9200, 'MF', 2, 0.87),
('마테오 코바치치',         6800, 'MF', 2, 0.64),

('요슈코 그바르디올',       8800, 'DF', 2, 0.84),
('루벤 디아스',            9700, 'DF', 2, 0.90),
('마누엘 아칸지',           7600, 'DF', 2, 0.72),
('리코 루이스',             5500, 'DF', 2, 0.62),

('에데르송',               8700, 'GK', 2, 0.89),

-- Team 3 (뮌헨)
('해리 케인',             13500, 'FW', 3, 0.97),
('루이스 디아스',          10200, 'FW', 3, 0.88),
('킹슬리 코망',             7800, 'FW', 3, 0.75),

('요수하 키미히',          12300, 'MF', 3, 0.95),
('레온 고레츠카',           9000, 'MF', 3, 0.86),
('무시알라',               11500, 'MF', 3, 0.93),

('알폰소 데이비스',          8800, 'DF', 3, 0.83),
('다요트 우파메카노',        9300, 'DF', 3, 0.90),
('김민재',                  8800, 'DF', 3, 0.87),
('하파엘 게레이로',          6200, 'DF', 3, 0.70),

('마누엘 노이어',            8600, 'GK', 3, 0.90),

-- Team 4 (유벤투스)
('두산 블라호비치',    13200, 'FW', 4, 0.96), 
('페데리코 키에사',    11200, 'FW', 4, 0.91), 
('케난 일디즈',         9800, 'FW', 4, 0.88), 

('더글라스 루이스',    10500, 'MF', 4, 0.92), 
('마누엘 로카텔리',     9800, 'MF', 4, 0.88), 
('니콜로 파조리',       8200, 'MF', 4, 0.82), 

('글레이손 브레머',     9600, 'DF', 4, 0.90), 
('다닐루',              8500, 'DF', 4, 0.84), 
('안드레아 캄비아소',   8200, 'DF', 4, 0.82), 
('페데리코 가티',       7800, 'DF', 4, 0.80), 

('미켈레 디 그레고리오', 9000, 'GK', 4, 0.90),

-- Team 5 (파리 생제르맹)
('우스만 뎀벨레',     11800, 'FW', 5, 0.98), 
('람달 콜로 무아니',   11000, 'FW', 5, 0.90), 
('곤살루 하무스',       9800, 'FW', 5, 0.88), 

('비티냐',              9200, 'MF', 5, 0.86), 
('마누엘 우가르테',      8800, 'MF', 5, 0.84), 
('파비안 루이스',        8200, 'MF', 5, 0.80), 

('마르퀴뇨스',         10200, 'DF', 5, 0.90), 
('프레스넬 킴펨베',      8600, 'DF', 5, 0.84), 
('루카스 에르난데스',    8800, 'DF', 5, 0.82), 
('아크라프 하키미',      9500, 'DF', 5, 0.89), 

('지안루이지 돈나룸마',  9400, 'GK', 5, 0.92);


-- MY_TEAM_PLAYER 테이블
CREATE TABLE MY_TEAM_PLAYER (
    POSITION_SLOT INT AUTO_INCREMENT NOT NULL COMMENT '선수위치배정',
    TEAM_ID INT  NOT NULL COMMENT '팀아이디',
    PLAYER_ID INT NOT NULL COMMENT '선수아이디',
    PRIMARY KEY (TEAM_ID, PLAYER_ID),
    FOREIGN KEY (TEAM_ID) REFERENCES MYTEAM(TEAM_ID) ON DELETE CASCADE,
    FOREIGN KEY (PLAYER_ID) REFERENCES PLAYER(PLAYER_ID) ON DELETE CASCADE
) COMMENT='내 팀 선수';
