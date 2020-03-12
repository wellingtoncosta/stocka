//
//  HomeView.swift
//  TodoApp
//
//  Created by Wellington Pereira on 12/03/20.
//  Copyright © 2020 Wellington Pereira. All rights reserved.
//

import UIKit
import SnapKit
import Todo

class HomeView : UIView {
    
    private let label: UILabel = {
        let label = UILabel()
        label.numberOfLines = 0
        label.lineBreakMode = .byWordWrapping
        label.text = CommonKt.createGreeting()
        label.backgroundColor = .white
        return label
    }()
    
    private func addConstraints() {
        label.snp.makeConstraints {
            $0.center.equalTo(self)
        }
    }
    
    private func buildViewHierarchy() {
        addSubview(label)
    }
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        backgroundColor = .white
        buildViewHierarchy()
        addConstraints()
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
}
