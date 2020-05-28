//
//  HomeViewController.swift
//  TodoApp
//
//  Created by Wellington Pereira on 12/03/20.
//  Copyright © 2020 Wellington Pereira. All rights reserved.
//

import UIKit
import shared

class ListTodosViewController: UIViewController, UITableViewDataSource, UITableViewDelegate {
    
    weak var coordinator: AppCoordinator?
    
    private lazy var presenter: ListTodosPresenter =
        (UIApplication.shared.delegate as! AppDelegate).listTodosPresenter
    
    private let cellId = "todoCellId"
    
    var todos : [Todo] = [Todo]() {
        didSet {
            (view as! ListTodosView).tableView.reloadData()
        }
    }

    override func loadView() {
        view = ListTodosView()
    }
    
    deinit {
        self.presenter.destroy()
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        setupNavigationBar()
        setupTableView()
    }
    
    override func viewDidAppear(_ animated: Bool) {
        super.viewDidAppear(animated)
        self.presenter.execute(callback: self.handleState)
    }
    
    private func handleState(state: ListTodosState) {
        switch state {
        case is Loading:
            loading(value: true)
            break
        case is Success:
            handleSuccess(success: state as! Success)
            break
        case is Error:
            handleError()
            break
        default:
            handleError()
        }
    }
    
    private func loading(value: Bool) {
        let view = (self.view as! ListTodosView)
        view.tableView.isHidden = value
        if value {
            view.loadingView.startAnimating()
        } else {
            view.loadingView.stopAnimating()
        }
    }
    
    private func handleSuccess(success: Success) {
        loading(value: false)
        todos = success.results
    }
    
    private func handleError() {
        loading(value: false)
        self.showAlert(title: "Error", message: "Unable to load todos.")
    }

    private func setupNavigationBar() {
        navigationController?.navigationBar.isHidden = false
        navigationController?.navigationBar.topItem?.title = "List of Todos"
        navigationController?.navigationBar.topItem?.rightBarButtonItem =
            UIBarButtonItem(title: "Add", style: .done, target: self, action: #selector(goToCreateNewTodoScreen))
    }

    @objc func goToCreateNewTodoScreen() {
        navigationController?.pushViewController(CreateNewTodoViewController(), animated: true)
    }

    private func setupTableView() {
        let tableView = (view as! ListTodosView).tableView
        tableView.translatesAutoresizingMaskIntoConstraints = false
        tableView.dataSource = self
        tableView.delegate = self
        tableView.register(TodoTableCell.self, forCellReuseIdentifier: cellId)
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: cellId, for: indexPath) as! TodoTableCell
        cell.todo = todos[indexPath.row]
        return cell
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return todos.count
    }

}
