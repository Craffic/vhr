DELIMITER $$

CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteDep`(IN did INT,OUT result INT)
BEGIN
  DECLARE ecount INT;
  DECLARE pid INT;
  DECLARE pcount INT;
  DECLARE a INT;
SELECT COUNT(*) INTO a FROM department WHERE id=did AND isParent=FALSE;
IF a=0 THEN SET result=-2;
ELSE
SELECT COUNT(*) INTO ecount FROM employee WHERE departmentId=did;
IF ecount>0 THEN SET result=-1;
ELSE
SELECT parentId INTO pid FROM department WHERE id=did;
DELETE FROM department WHERE id=did AND isParent=FALSE;
SELECT ROW_COUNT() INTO result;
SELECT COUNT(*) INTO pcount FROM department WHERE parentId=pid;
IF pcount=0 THEN UPDATE department SET isParent=FALSE WHERE id=pid;
END IF;
END IF;
END IF;
END $$
DELIMITER ;

