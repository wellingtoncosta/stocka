//
//  CustomView.swift
//  TodoApp
//
//  Created by Wellington Pereira on 22/04/20.
//  Copyright © 2020 Wellington Pereira. All rights reserved.
//

import Foundation

protocol CustomView {
    func buildViewHierarchy()
    func setupConstraints()
    func setupAdditionalConfiguration()
    func setupView()
}

extension CustomView {
    func setupView() {
        buildViewHierarchy()
        setupConstraints()
        setupAdditionalConfiguration()
    }
}
