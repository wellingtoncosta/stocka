//
//  HomeView.swift
//  TodoApp
//
//  Created by Wellington Pereira on 12/03/20.
//  Copyright © 2020 Wellington Pereira. All rights reserved.
//

import UIKit
import SnapKit

final class ListTodosView : UIView {
    
    let todosTableView: UITableView = {
        let tableview = UITableView()
        return tableview
    }()
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        setupView()
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
}

extension ListTodosView : CustomView {
    func buildViewHierarchy() {
        addSubview(todosTableView)
    }
    
    func setupConstraints() {
        todosTableView.topAnchor.constraint(equalTo:self.safeAreaLayoutGuide.topAnchor).isActive = true
        todosTableView.leftAnchor.constraint(equalTo:self.safeAreaLayoutGuide.leftAnchor).isActive = true
        todosTableView.rightAnchor.constraint(equalTo:self.safeAreaLayoutGuide.rightAnchor).isActive = true
        todosTableView.bottomAnchor.constraint(equalTo:self.safeAreaLayoutGuide.bottomAnchor).isActive = true
    }
    
    func setupAdditionalConfiguration() {
        backgroundColor = .white
        todosTableView.tableFooterView = UIView()
    }
    
}
