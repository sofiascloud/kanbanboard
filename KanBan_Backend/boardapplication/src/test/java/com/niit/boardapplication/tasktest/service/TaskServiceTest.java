package com.niit.boardapplication.tasktest.service;

import com.niit.boardapplication.exception.TaskAlreadyExistsException;
import com.niit.boardapplication.exception.TaskNotFoundException;
import com.niit.boardapplication.model.Task;
import com.niit.boardapplication.repository.KanBanRepository;
import com.niit.boardapplication.service.KanBanServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

    @Mock //dependency
    private KanBanRepository kanBanRepository;

    @InjectMocks //dependent
    private KanBanServiceImpl kanBanService;

    //model class reference creation
    private Task task;

    //run before each testcase
    @BeforeEach
    public void PreTest() {
        task=new Task(111,"Project","Capstone Project (KanBan Board Application)",
                "23/05/2022","High","NIIT",
                "In-Progress",10,"Dan9191@gmail.com");
    }

    //run after each test case
    @AfterEach
    public void postTest() {
        task= null;
    }

    @Test
    public void testAddTaskReturnTaskNegation() throws TaskAlreadyExistsException {
        when(kanBanRepository.findById(task.getTaskId())).thenReturn(Optional.ofNullable(task));
        assertThrows(TaskAlreadyExistsException.class,()->kanBanService.addUserTask(task));
        verify(kanBanRepository,times(1)).findById(task.getTaskId());
        verify(kanBanRepository,times(0)).save(task);

    }

    @Test
    public void testTaskDeleteNegation()throws TaskNotFoundException {
        when(kanBanRepository.findById(task.getTaskId())).thenReturn(Optional.ofNullable(null));
        assertThrows(TaskNotFoundException.class, ()->kanBanService.removeTask(task));
        verify(kanBanRepository,times(1)).findById(task.getTaskId());
        verify(kanBanRepository,times(0)).deleteById(task.getTaskId());

    }

//    @Test
//    public void testAddTaskReturnTask() throws TaskAlreadyExistsException{
//        when(kanBanRepository.findById(task.getTaskId())).thenReturn(Optional.ofNullable(null));
//        when(kanBanRepository.save(product)).thenReturn(product);
//        assertEquals(product,productServiceimpl.addProduct(product));
//        verify(kanBanRepository,times(1)).findById(product.getProductCode());
//        verify(kanBanRepository,times(1)).save(product);
//
//    }



}
