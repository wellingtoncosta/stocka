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
    
    let cellId = "todoCellId"
    
    var todos : [Todo] = [Todo]() {
        didSet {
            (view as! ListTodosView).todosTableView.reloadData()
        }
    }

    override func loadView() {
        view = ListTodosView()
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        setupNavigationBar()
        setupTableView()
    }
    
    override func viewDidAppear(_ animated: Bool) {
        todos = (UIApplication.shared.delegate as! AppDelegate).repository.fetchAll()
    }

    private func setupNavigationBar() {
        navigationController?.navigationBar.isHidden = false
        navigationController?.navigationBar.topItem?.title = "List of Todos"
        let addItem = UIBarButtonItem(title: "Add", style: .done, target: self, action: #selector(goToCreateNewTodoScreen))
        navigationController?.navigationBar.topItem?.rightBarButtonItem = addItem
    }
    
    @objc func goToCreateNewTodoScreen() {
        navigationController?.pushViewController(CreateNewTodoViewController(), animated: true)
    }
    
    private func setupTableView() {
        let tableView = (view as! ListTodosView).todosTableView
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
