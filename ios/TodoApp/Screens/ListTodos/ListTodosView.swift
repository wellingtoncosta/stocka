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
    
    let loadingView: UIActivityIndicatorView = {
        let loadingView = UIActivityIndicatorView()
        loadingView.color = .gray
        loadingView.style = .large
        return loadingView
    }()
    
    let tableView: UITableView = {
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
        addSubview(tableView)
        addSubview(loadingView)
    }
    
    func setupConstraints() {
        loadingView.snp.makeConstraints {
            $0.centerY.equalTo(self)
            $0.centerX.equalTo(self)
        }
        
        tableView.snp.makeConstraints {
            $0.left.equalToSuperview()
            $0.top.equalToSuperview()
            $0.right.equalToSuperview()
            $0.bottom.equalToSuperview()
        }
    }
    
    func setupAdditionalConfiguration() {
        backgroundColor = .white
        tableView.tableFooterView = UIView()
    }
    
}
