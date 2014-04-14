//
//  APPMasterViewController.h
//  RSSreader
//
//  Created by Joshua O'Steen on 4/28/13.
//  Copyright (c) 2013 Joshua O'Steen. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface APPMasterViewController : UITableViewController <NSXMLParserDelegate>

// property for table view outlet // 
@property (strong, nonatomic) IBOutlet UITableView *tableView;

@end
