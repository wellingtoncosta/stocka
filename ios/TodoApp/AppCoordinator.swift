//
//  AppCoordinator.swift
//  TodoApp
//
//  Created by Wellington Pereira on 12/03/20.
//  Copyright © 2020 Wellington Pereira. All rights reserved.
//

import UIKit

protocol Coordinator : AnyObject{
    
    var currentViewController: UIViewController? { get }
    
    var navigationController: UINavigationController { get }
    
    func start()
    
}

class AppCoordinator : Coordinator {
    
    var currentViewController: UIViewController?
    
    var navigationController: UINavigationController
    
    init(navigationController: UINavigationController) {
        self.navigationController = navigationController
    }
    
    func start() {
        let viewController = HomeViewController()

        viewController.coordinator = self

        currentViewController = viewController

        navigationController.viewControllers = [viewController]
    }
    
}
