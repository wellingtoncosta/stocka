//
//  HomeViewController.swift
//  TodoApp
//
//  Created by Wellington Pereira on 12/03/20.
//  Copyright © 2020 Wellington Pereira. All rights reserved.
//


import UIKit

class HomeViewController: UIViewController {
    
    weak var coordinator: AppCoordinator?

    override func loadView() {
        view = HomeView()
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        navigationController?.navigationBar.isHidden = true
    }

}
