//
//  DataManager.swift
//  TodoApp
//
//  Created by Wellington Pereira on 12/03/20.
//  Copyright © 2020 Wellington Pereira. All rights reserved.
//

import Foundation
import CoreData

public final class DataManager {
    
    // MARK: - Properties
    
    private static var _persistentContainer: NSPersistentContainer?
    
    // MARK: - Setup
    
    public static func setup() {
        let container = NSPersistentContainer(name: "TodoApp")
        container.loadPersistentStores(completionHandler: { (storeDescription, error) in
            if let error = error as NSError? {
                fatalError("Unresolved error \(error), \(error.userInfo)")
            }
        })
        DataManager._persistentContainer = container
    }
    
    // MARK: - Obtain NSPersistentContainer instance
    
    static func persistentContainer() -> NSPersistentContainer {
        if _persistentContainer != nil {
            return _persistentContainer!
        } else {
            fatalError("DataManager must be initialized.")
        }
    }
    
    // MARK: - Save context

    public static func saveContext () {
        let context = _persistentContainer!.viewContext
        if context.hasChanges {
            do {
                try context.save()
            } catch {
                // Replace this implementation with code to handle the error appropriately.
                // fatalError() causes the application to generate a crash log and terminate. You should not use this function in a shipping application, although it may be useful during development.
                let nserror = error as NSError
                fatalError("Unresolved error \(nserror), \(nserror.userInfo)")
            }
        }
    }
    
}
