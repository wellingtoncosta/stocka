//
//  TodoTableCell.swift
//  TodoApp
//
//  Created by Wellington Pereira on 14/03/20.
//  Copyright © 2020 Wellington Pereira. All rights reserved.
//

import UIKit
import shared

class TodoTableCell : UITableViewCell {
    
    var todo: Todo? {
        didSet {
            guard let todoItem = todo else { return }
            titleLabel.text = todoItem.title
            statusLabel.text = "Status: \(todoItem.status.capitalized())"
            if let details = todoItem.description {
                detailsLabel.isHidden = false
                detailsLabel.text = details
            } else {
                detailsLabel.isHidden = true
            }
        }
    }
    
    private let titleLabel: UILabel = {
        let label = UILabel()
        label.font = UIFont.boldSystemFont(ofSize: 16)
        label.textColor =  .black
        label.translatesAutoresizingMaskIntoConstraints = false
        return label
    }()
    
    private let detailsLabel: UILabel = {
        let label = UILabel()
        label.font = UIFont.boldSystemFont(ofSize: 14)
        label.textColor =  .gray
        label.translatesAutoresizingMaskIntoConstraints = false
        return label
    }()
    
    private let statusLabel: UILabel = {
        let label = UILabel()
        label.font = UIFont.boldSystemFont(ofSize: 14)
        label.textColor =  .gray
        label.translatesAutoresizingMaskIntoConstraints = false
        return label
    }()
    
    override init(style: UITableViewCell.CellStyle, reuseIdentifier: String?) {
        super.init(style: style, reuseIdentifier: reuseIdentifier)
        setupView()
    }
    
    required init?(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)
    }
    
}

extension TodoTableCell : CustomView {
    func buildViewHierarchy() {
        contentView.addSubview(titleLabel)
        contentView.addSubview(detailsLabel)
        contentView.addSubview(statusLabel)
    }
    
    func setupConstraints() {
        titleLabel.snp.makeConstraints {
            $0.top.equalTo(contentView).offset(16)
            $0.leading.equalTo(16)
            $0.trailing.equalTo(-16)
        }

        detailsLabel.snp.makeConstraints {
            $0.top.equalTo(titleLabel.snp.bottom).offset(6)
            $0.leading.equalTo(16)
            $0.trailing.equalTo(-16)
        }

        statusLabel.snp.makeConstraints {
            $0.top.equalTo(detailsLabel.snp.bottom).offset(6)
            $0.bottom.equalTo(contentView.snp.bottom).offset(-16)
            $0.leading.equalTo(16)
            $0.trailing.equalTo(-16)
        }
    }
    
    func setupAdditionalConfiguration() {
        selectionStyle = .none
    }
    
}
