package DataBase;

public class GenerateId {

    private static long groupId = 0;
    private static long lessonId = 0;
    private static long studentId = 0;

    public static Long generateGroupId() {
        return ++groupId;
    }

    public static Long generateLessonId() {
        return ++lessonId;
    }

    public static Long generateStudentId() {
        return ++studentId;
    }
}
