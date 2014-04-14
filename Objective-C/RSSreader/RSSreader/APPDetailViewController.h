//
//  APPDetailViewController.h
//  RSSreader
//
//  Created by Joshua O'Steen on 4/28/13.
//  Copyright (c) 2013 Joshua O'Steen. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface APPDetailViewController : UIViewController

// properties for url string and web view outlet //
@property (copy, nonatomic) NSString *url;
@property (strong, nonatomic) IBOutlet UIWebView *webView;


@end
