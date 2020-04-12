//
//  HomeView.swift
//  TodoApp
//
//  Created by Wellington Pereira on 12/03/20.
//  Copyright © 2020 Wellington Pereira. All rights reserved.
//

import UIKit
import SnapKit
import shared

class ListTodosView : UIView {
    
    let todosTableView: UITableView = {
        let tableview = UITableView()
        return tableview
    }()
    
    private func addConstraints() {
        todosTableView.topAnchor.constraint(equalTo:self.safeAreaLayoutGuide.topAnchor).isActive = true
        todosTableView.leftAnchor.constraint(equalTo:self.safeAreaLayoutGuide.leftAnchor).isActive = true
        todosTableView.rightAnchor.constraint(equalTo:self.safeAreaLayoutGuide.rightAnchor).isActive = true
        todosTableView.bottomAnchor.constraint(equalTo:self.safeAreaLayoutGuide.bottomAnchor).isActive = true
    }
    
    private func buildViewHierarchy() {
        addSubview(todosTableView)
    }
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        backgroundColor = .white
        todosTableView.tableFooterView = UIView()
        buildViewHierarchy()
        addConstraints()
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
}
