//
//  UIViewController+Extension.swift
//  TodoApp
//
//  Created by Wellington Pereira on 5/27/20.
//  Copyright © 2020 Wellington Pereira. All rights reserved.
//

import UIKit

extension UIViewController {
    
    func showAlert(title: String, message: String) {
        let alertController = UIAlertController(title: title, message: message, preferredStyle: .alert)
        alertController.addAction(UIAlertAction(title: "Dismiss", style: .default))
        self.present(alertController, animated: true, completion: nil)
    }
    
}
