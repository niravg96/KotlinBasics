package com.example.kotlinbasics;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StudentCustomAdapter extends RecyclerView.Adapter<StudentCustomAdapter.StudentViewHolder> {
    
    List<StudentModel> studentlist;
    StudentModel studentModel;

    public StudentCustomAdapter(List<StudentModel> studentlist){
        this.studentlist =studentlist;
    }

    
    @NonNull
    @Override
    public StudentCustomAdapter.StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.custom_student_layout,parent,false);
        return new StudentViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull StudentCustomAdapter.StudentViewHolder holder, int position) {

        studentModel = studentlist.get(position);
        holder.tvid.setText(studentModel.getId());
        holder.tvname.setText(studentModel.getName());

    }
    @Override
    public int getItemCount() {
        return studentlist.size();
    }
    class StudentViewHolder extends  RecyclerView.ViewHolder{

        TextView tvname,tvid;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);

            tvid  =(TextView) itemView.findViewById(R.id.tvid);
            tvname  =(TextView) itemView.findViewById(R.id.tvname);
        }
    }
}
