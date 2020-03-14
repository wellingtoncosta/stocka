//
//  TodoRepositoryImpl.swift
//  TodoApp
//
//  Created by Wellington Pereira on 12/03/20.
//  Copyright © 2020 Wellington Pereira. All rights reserved.
//

import CoreData
import Todo

class TodoRepositoryImp : TodoRepository {
    
    func fetchAll() -> [Todo] {
        var todos = [Todo]()
        
        let request = NSFetchRequest<NSFetchRequestResult>(entityName: "TodoEntity")
        
        request.returnsObjectsAsFaults = false
        
        do {
            let context = DataManager.persistentContainer().viewContext
            let result = try context.fetch(request)
            for data in result as! [NSManagedObject] {
                let todo = Todo(
                    id: KotlinInt.init(int: data.value(forKey: "id") as! Int32),
                    title: (data.value(forKey: "title") as! String),
                    details: (data.value(forKey: "details") as! String),
                    status: TodoStatusKt.fromValue(value: data.value(forKey: "status") as! Int32)
                )
                todos.append(todo)
            }
        } catch let error {
            print("Failed to load todos: \(error.localizedDescription)")
        }
        
        return todos
    }
    
    func save(todo: Todo) {
        do {
            let context = DataManager.persistentContainer().viewContext
            let entity = NSEntityDescription.entity(forEntityName: "TodoEntity", in: context)
            let newTodo = NSManagedObject(entity: entity!, insertInto: context)
            
            newTodo.setValue(getNextId(), forKey: "id")
            newTodo.setValue(todo.title, forKey: "title")
            newTodo.setValue(todo.details, forKey: "details")
            newTodo.setValue(todo.status.value, forKey: "status")
            
            try context.save()
        } catch let error {
            print("Failed to save a new todo: \(error.localizedDescription)")
        }
    }
    
    private func getNextId() -> Int {
        do {
            let context = DataManager.persistentContainer().viewContext
            let request = NSFetchRequest<NSFetchRequestResult>(entityName: "TodoEntity")
            let result = try context.fetch(request)
            return result.count + 1
        } catch let error {
            fatalError("Failed to get next id: \(error.localizedDescription)")
        }
    }
    
    func updateStatus(todoId: Int64, staatus: TodoStatus) {
        // TODO
    }
    
}
