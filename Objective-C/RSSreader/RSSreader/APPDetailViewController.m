//
//  APPDetailViewController.m
//  RSSreader
//
//  Created by Joshua O'Steen on 4/28/13.
//  Copyright (c) 2013 Joshua O'Steen. All rights reserved.
//

#import "APPDetailViewController.h"


@implementation APPDetailViewController

#pragma mark - Managing the detail item

// method to load url in web view // 
- (void)viewDidLoad
{
    [super viewDidLoad];
    
    // assign properly converted url to url pointer // 
    NSURL *myURL = [NSURL URLWithString: [self.url stringByAddingPercentEscapesUsingEncoding: NSUTF8StringEncoding]];
    
    // assign requested url to request pointer, provides cache and default timeout // 
    NSURLRequest *request = [NSURLRequest requestWithURL:myURL];
    
    // load requested url into web view // 
    [self.webView loadRequest:request];
}

@end
