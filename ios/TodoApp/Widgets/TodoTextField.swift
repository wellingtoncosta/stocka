//
//  TodoTextField.swift
//  TodoApp
//
//  Created by Wellington Pereira on 14/03/20.
//  Copyright © 2020 Wellington Pereira. All rights reserved.
//

import UIKit

final class TodoTextField : UIView {
    
    private let label: UILabel = {
        let label = UILabel()
        label.font = .boldSystemFont(ofSize: 12)
        return label
    }()
    
    private let backgroundView: UIView = {
        let view = UIView()
        view.layer.borderWidth = 1
        return view
    }()
    
    private let field = UITextField()
    
    var labelText: String? {
        didSet { label.text = labelText }
    }
    
    var labelTextColor: UIColor? {
        didSet { label.textColor = labelTextColor }
    }
    
    var fieldTintColor: UIColor? {
        didSet { field.tintColor = fieldTintColor }
    }
    
    var fieldTextColor: UIColor? {
        didSet { field.textColor = fieldTextColor }
    }
    
    var fieldBorderColor: UIColor? {
        didSet { backgroundView.layer.borderColor = fieldBorderColor?.cgColor }
    }
    
    var value: String {
        get { return field.text ?? "" }
        set { }
    }
    
    init() {
        super.init(frame: .zero)
        setupView()
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
}

extension TodoTextField : CustomView {
    func buildViewHierarchy() {
        addSubview(label)
        addSubview(backgroundView)
        backgroundView.addSubview(field)
    }
    
    func setupConstraints() {
        label.snp.makeConstraints {
            $0.top.equalToSuperview()
            $0.trailing.equalTo(0)
            $0.leading.equalTo(0)
        }
        
        backgroundView.snp.makeConstraints {
            $0.trailing.equalTo(0)
            $0.leading.equalTo(0)
            $0.top.equalTo(label.snp.bottom).offset(8)
            $0.bottom.equalToSuperview()
            $0.height.equalTo(44)
        }

        field.snp.makeConstraints {
            $0.trailing.equalTo(backgroundView.snp.trailing).offset(8)
            $0.leading.equalTo(backgroundView.snp.leading).offset(8)
            $0.top.equalTo(backgroundView.snp.top)
            $0.bottom.equalTo(backgroundView.snp.bottom)
        }
    }
    
    func setupAdditionalConfiguration() { }
    
}
