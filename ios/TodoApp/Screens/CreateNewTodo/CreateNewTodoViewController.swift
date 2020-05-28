//
//  CreateNewTodoViewController.swift
//  TodoApp
//
//  Created by Wellington Pereira on 13/03/20.
//  Copyright © 2020 Wellington Pereira. All rights reserved.
//

import UIKit
import shared

class CreateNewTodoViewController : UIViewController {
    
    private lazy var presenter: CreateNewTodoPresenter =
        (UIApplication.shared.delegate as! AppDelegate).createNewTodoPresenter
    
    override func loadView() {
        view = CreateNewTodoView()
    }
    
    deinit {
        self.presenter.destroy()
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
        
        let todo = Todo.init(
            id: nil,
            title: view.titleField.value as String,
            description: view.detailsField.value as String,
            status: TodoStatus.planned
        )
        
        (UIApplication.shared.delegate as! AppDelegate).repository!.save(todo: todo)
        
        navigationController?.popViewController(animated: true)
    }

    private func handleState(state: CreateNewTodoState) {
        switch state {
        case is Loading_:
            saving(value: true)
            break
        case is Success_:
            handleSuccess()
            break
        case is Error_:
            handleError()
            break
        default:
            handleError()
        }
    }
    
    private func saving(value: Bool) {
        let view = (self.view as! CreateNewTodoView)
        view.titleField.isHidden = value
        view.detailsField.isHidden = value
        if value {
            view.loadingView.startAnimating()
        } else {
            view.loadingView.stopAnimating()
        }
    }
    
    private func handleSuccess() {
        saving(value: false)
        navigationController?.popViewController(animated: true)
    }
    
    private func handleError() {
        saving(value: false)
        self.showAlert(title: "Error", message: "Unable to save todos.")
    }
    
}
