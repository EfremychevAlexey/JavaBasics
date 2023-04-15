package main;

import main.model.Task;
import main.model.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class TaskController {

    @Autowired
    TaskRepository taskRepository;

    /*Метод должен создавать задачу с указанными в запросе названием и описанием. Дата и время создания (creationTime),
    а также значение поля isDone должны устанавливаться автоматически.
    Ответ не должен содержать тело и должен иметь код HTTP-ответа 201
     */
    @PostMapping("/tasks")
    public ResponseEntity add(Task task){
        taskRepository.save(task);
        return  ResponseEntity.status(HttpStatus.OK).body(task.getId());
    }

/*Метод должен возвращать информацию о задаче, ID которой передан ему в качестве параметра*/
    @GetMapping("/tasks/{id}")
    public ResponseEntity getTask(@PathVariable int id){
        Optional<Task> optionalTask = taskRepository.findById(id);
        if(!optionalTask.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity(optionalTask.get(), HttpStatus.OK);
    }

/*Метод должен возвращать список всех задач в формате JSON:*/
    @GetMapping("/tasks")
    public List<Task> taskList(){
        Iterable<Task> taskIterable = taskRepository.findAll();
        ArrayList<Task> tasks = new ArrayList<>();
        for(Task task : taskIterable){
            tasks.add(task);
        }
        return tasks;
    }

    @PatchMapping("/tasks/{id}")
    public ResponseEntity setTask(@PathVariable int id, boolean isDone, String title, String description){
        Optional<Task> taskOptional = taskRepository.findById(id);
        Task task;
        if(taskOptional.isPresent()){
            task = taskOptional.get();
            task.setDone(isDone);
            task.setTitle(title);
            task.setDescription(description);
            taskRepository.save(task);
            return new ResponseEntity(HttpStatus.OK);
        }
        else{
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity taskDelete(@PathVariable int id){
        Optional<Task> taskOptional = taskRepository.findById(id);
        if(taskOptional.isPresent()){
            taskRepository.delete(taskOptional.get());
            return new ResponseEntity(HttpStatus.OK);
        }
        else{
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

}
