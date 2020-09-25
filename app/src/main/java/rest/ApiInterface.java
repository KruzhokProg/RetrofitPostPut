package rest;

import java.util.List;
import model.Student;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    @POST("api/students")
    Call<Student> createStudent(@Body Student student);
    @GET("api/post/savestudent")
    Call<Student> createStudentAsGet(@Query("Name") String name, @Query("Email") String email, @Query("FacultyId") Integer facultyId);
    @PUT("api/students/{Id}")
    Call<Student> changeStudent(@Path("Id") int Id, @Body Student student);
    @GET("api/post")
    Call<List<String>> getStudentByFirstLetters(@Query("sym") String letter, @Query("facultyId") Integer facultyId);
    @GET("api/students")
    Call<List<String>> getStudents();
    @DELETE("api/post/{id}")
    Call<ResponseBody> deleteStudent(@Path("id") int id);
//    @GET("api/post/takestudent")
//    Call<String[]> searchStudents(@Query("sym") String[] syms, @Query("facultyId") Integer facultyId);
    //Call<List<String>> searchStudents(@Query("sym") String sym0, @Query("sym") String sym1, @Query("facultyId") Integer facultyId);
}

