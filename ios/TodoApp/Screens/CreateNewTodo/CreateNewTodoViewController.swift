//
//  CreateNewTodoViewController.swift
//  TodoApp
//
//  Created by Wellington Pereira on 13/03/20.
//  Copyright © 2020 Wellington Pereira. All rights reserved.
//

import UIKit
import Todo

class CreateNewTodoViewController : UIViewController {
    
    override func loadView() {
        view = CreateNewTodoView()
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        setupNavigationBar()
    }
    
    private func setupNavigationBar() {
        navigationController?.navigationBar.isHidden = false
        navigationItem.title = "Create New Todo"
        navigationItem.leftBarButtonItem = UIBarButtonItem(
            image: UIImage(named: "BackArrow"),
            style: .plain, target: navigationController,
            action: #selector(UINavigationController.popViewController(animated:))
        )
        
        navigationItem.rightBarButtonItem = UIBarButtonItem(
            title: "Save", style: .done, target: self, action: #selector(saveNewTodo)
        )
    }
    
    @objc func saveNewTodo() {
        let view = (self.view as! CreateNewTodoView)
        
        let todo = Todo(
            id: nil,
            title: view.titleField.value as String,
            details: view.detailsField.value as String,
            status: TodoStatus.planned
        )
        
        (UIApplication.shared.delegate as! AppDelegate).repository.save(todo: todo)
        
        navigationController?.popViewController(animated: true)
    }
    
}
