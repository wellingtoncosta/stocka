//
//  UITextField+Extension.swift
//  TodoApp
//
//  Created by Wellington Pereira on 14/03/20.
//  Copyright © 2020 Wellington Pereira. All rights reserved.
//

import UIKit

extension UITextField {
    
    func setPlaceHolder(text: String, color: UIColor) {
        self.attributedPlaceholder = NSAttributedString(
            string: text,
            attributes: [NSAttributedString.Key.foregroundColor: color]
        )
    }
    
}
