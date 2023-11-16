package com.example.tasktracker.Controller;

import com.example.tasktracker.ApiResponse.ApiResponse;
import com.example.tasktracker.Model.Task;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/tt")
public class TaskTrackerController {

        List<Task> tasks = new ArrayList<>();

        @GetMapping("/get")
        public List<Task> getTasks(){
            return tasks;
        }

        @PutMapping("/update/{index}")
        public ApiResponse updateTask(@RequestBody Task task, @PathVariable int index){
            if(index == -1 || index>tasks.size()-1){
                return new ApiResponse("not found",404);
            }else {
                tasks.set(index,task);
                return new ApiResponse("updated",200);
            }

        }

       @PostMapping("/add")
        public ApiResponse addTask(@RequestBody Task task){
            tasks.add(task);
           return new ApiResponse("added",200);
       }

       @DeleteMapping("/delete/{index}")
        public ApiResponse deleteTask(@PathVariable int index){
           if(index == -1 || index>tasks.size()-1){
               return new ApiResponse("not found",404);
           }else {

           }
            tasks.remove(index);
           return new ApiResponse("deleted",200);
       }

       @PutMapping("/change/{index}")
        public ApiResponse changeStatus(@PathVariable int index){
           if(index == -1 || index>tasks.size()-1){
               return new ApiResponse("not found",404);
           }else {
               if(tasks.get(index).getStatus().equals("not done")) tasks.get(index).setStatus("done");
               else tasks.get(index).setStatus("not done");

               return new ApiResponse("changed",200);
           }

       }

       @GetMapping("/search/{title}")
    public Task searchTask(@PathVariable String title){
        for(Task t : tasks){
            if(t.getTitle().equals(title)) return t;
        }
        return null;
       }




}
