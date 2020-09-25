package com.example.retrofitpostput;

import adapter.StudentAdapter;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import model.Student;
import okhttp3.ResponseBody;
import rest.ApiClient;
import rest.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ApiInterface apiService;
    EditText etName, etEmail, etFacultyId, etId;
    RecyclerView rv;
    StudentAdapter adapter;
    List<String> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.editTextName);
        etEmail = findViewById(R.id.editTextEmail);
        etFacultyId = findViewById(R.id.editTextFacultyId);
        etId = findViewById(R.id.etId);
        rv = findViewById(R.id.rv);

        apiService = ApiClient.getClient().create(ApiInterface.class);
/*
        Call<String[]> call = apiService.searchStudents(new String[]{"А"}, 1);
        call.enqueue(new Callback<String[]>() {
            @Override
            public void onResponse(Call<String[]> call, Response<String[]> response) {
                String[] data = response.body();
            }

            @Override
            public void onFailure(Call<String[]> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }); */
//----------------------------------------------------------------------------------------------
//        Call<List<String>> call = apiService.searchStudents(Arrays.asList("А","О"), 1);
//        call.enqueue(new Callback<List<String>>() {
//            @Override
//            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
//                List<String> data = response.body();
//            }
//
//            @Override
//            public void onFailure(Call<List<String>> call, Throwable t) {
//
//            }
//        });
        FillRV();
    }


    public void FillRV()
    {
        Call<List<String>> call = apiService.getStudents();
        call.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                data = response.body();
                adapter = new StudentAdapter(data, getApplicationContext());
                rv.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void btnAdd_onClick(View view) {
        String id = etId.getText().toString();
        String name = etName.getText().toString();
        String email = etEmail.getText().toString();
        Integer facultyId = Integer.parseInt(etFacultyId.getText().toString());

//        Call<Student> call = apiService.createStudentAsGet(name, email, facultyId);
//        call.enqueue(new Callback<Student>() {
//            @Override
//            public void onResponse(Call<Student> call, Response<Student> response) {
//                Toast.makeText(MainActivity.this, "Пользователь добавлен!", Toast.LENGTH_SHORT).show();
//                FillRV();
//            }
//
//            @Override
//            public void onFailure(Call<Student> call, Throwable t) {
//                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//        -----------------------
        Call<Student> call = apiService.createStudent(new Student(name, email, facultyId));

        call.enqueue(new Callback<Student>() {
            @Override
            public void onResponse(Call<Student> call, Response<Student> response) {
                if(!response.isSuccessful())
                {
                    Toast.makeText(MainActivity.this, "Code: " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Пользователь успешно добавлен! Code: " + response.code(), Toast.LENGTH_SHORT).show();
                    FillRV();
                }
            }

            @Override
            public void onFailure(Call<Student> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void btnEdit_onClick(View view) {
        Integer id = Integer.parseInt(etId.getText().toString());
        String name = etName.getText().toString();
        String email = etEmail.getText().toString();
        Integer facultyId = Integer.parseInt(etFacultyId.getText().toString());
        Call<Student> call = apiService.changeStudent(id, new Student(id, name, email, facultyId));
        call.enqueue(new Callback<Student>() {
            @Override
            public void onResponse(Call<Student> call, Response<Student> response) {
                if(!response.isSuccessful())
                {
                    Toast.makeText(MainActivity.this, "Code: " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Пользователь успешно изменен! Code: " + response.code(), Toast.LENGTH_SHORT).show();
                    FillRV();
                }
            }

            @Override
            public void onFailure(Call<Student> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void btnShow_Click(View view) {
        String name = etName.getText().toString();
        Integer facultyId = Integer.parseInt(etFacultyId.getText().toString());
        Call<List<String>> call = apiService.getStudentByFirstLetters(name, facultyId);
        call.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                if(!response.isSuccessful())
                {
                    Toast.makeText(MainActivity.this, "Code: " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                else
                {
                    data.clear();
                    data.addAll(response.body());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
            }
        });

    }

    public void btnDelete_Click(View view) {
        final Integer id = Integer.parseInt(etName.getText().toString());
        Call<ResponseBody> call = apiService.deleteStudent(id);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Toast.makeText(MainActivity.this, "элемент с id: " + id + " удален", Toast.LENGTH_SHORT).show();
                FillRV();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
}
