CREATE TRIGGER [dbo].[trg_AutoCalculateGPA]
ON [dbo].[Enrollments]
AFTER INSERT, UPDATE, DELETE
    AS
BEGIN
    SET NOCOUNT ON;

    DECLARE @AffectedStudents TABLE (StudentID INT);

INSERT INTO @AffectedStudents
SELECT StudentID FROM inserted
UNION
SELECT StudentID FROM deleted;