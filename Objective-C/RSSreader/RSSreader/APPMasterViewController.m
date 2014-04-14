//
//  APPMasterViewController.m
//  RSSreader
//
//  Created by Joshua O'Steen on 4/28/13.
//  Copyright (c) 2013 Joshua O'Steen. All rights reserved.
//

#import "APPMasterViewController.h"
#import "TableHeaderView.h"
#import "APPDetailViewController.h"

@interface APPMasterViewController ()
{
    
    // class declarations //
    NSXMLParser *parser;
    NSMutableArray *feeds;
    NSMutableDictionary *item;
    NSMutableString *title;
    NSMutableString *link;
    NSString *element;
    
}

@end

@implementation APPMasterViewController

// method to load nib file // 
- (void)awakeFromNib
{
    [super awakeFromNib];
}

// method to load view //
- (void)viewDidLoad
{
    [super viewDidLoad];
	// uncomment to add nav back button and edit button //
    /*self.navigationItem.leftBarButtonItem = self.editButtonItem;

    UIBarButtonItem *addButton = [[UIBarButtonItem alloc] initWithBarButtonSystemItem:UIBarButtonSystemItemAdd target:self action:@selector(insertNewObject:)];
    self.navigationItem.rightBarButtonItem = addButton;*/
    
    // initialize feeds pointer with array contents //
    feeds = [[NSMutableArray alloc] init];
    
    // set feed url to url string pointer //
    NSURL *url = [NSURL URLWithString:@"http://www.engadget.com/rss.xml"];
    
    // initialize parser with XML content given by url pointer //
    parser = [[NSXMLParser alloc] initWithContentsOfURL:url];
    
    // set delegate //
    [parser setDelegate:self];
    
    // begin parsing of XML content
    [parser parse];
    
    // add the .png to top of table view, initialize header with title of RSS feed //
    self.tableView.tableHeaderView = [[TableHeaderView alloc] initWithText:@"ENGADGET"];

}

// method for memory warning // 
- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}



#pragma mark - Table View

// method to return number of sections in table //
- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView
{
    return 1;
}

// method to determing how many rows based on count of feeds //
- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section
{
    return feeds.count;
}

// method to edit row cells //
- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
    // reuse cell for next row of content //
    UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:@"Cell" forIndexPath:indexPath];
    
    // add parsed XML title to row cell // 
    cell.textLabel.text = [[feeds objectAtIndex:indexPath.row] objectForKey: @"title"];
    
    // uncomment to add icon image to each cell instead of just the header //
    //[cell.imageView setImage:[UIImage imageWithData:[NSData dataWithContentsOfURL:[NSURL URLWithString:@"http://cdn-img.easyicon.net/png/5388/538877.gif"]]]];//
    
    return cell;
    
}

// method to begin parse XML content 
- (void)parser:(NSXMLParser *)parser didStartElement:(NSString *)elementName namespaceURI:(NSString *)namespaceURI qualifiedName:(NSString *)qName attributes:(NSDictionary *)attributeDict
{
    
    
    element = elementName;
    
    // if element is found to be 'item', assign item, title, and link //
    if ([element isEqualToString:@"item"])
    {
        
        item    = [[NSMutableDictionary alloc] init];
        title   = [[NSMutableString alloc] init];
        link    = [[NSMutableString alloc] init];
        
    }
    
}

// method to append title and link //
- (void)parser:(NSXMLParser *)parser foundCharacters:(NSString *)string
{
    
    // append title and link //
    if ([element isEqualToString:@"title"])
    {
        [title appendString:string];
    }
    else if ([element isEqualToString:@"link"])
    {
        [link appendString:string];
    }
    
}

// method to end parse XML content // 
- (void)parser:(NSXMLParser *)parser didEndElement:(NSString *)elementName namespaceURI:(NSString *)namespaceURI qualifiedName:(NSString *)qName
{
    
    // if end of element is found, end parse //
    if ([elementName isEqualToString:@"item"])
    {
        
        [item setObject:title forKey:@"title"];
        [item setObject:link forKey:@"link"];
        
        [feeds addObject:[item copy]];
        
    }
    
}

// method to send link in segue to be opened in web view //
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender
{
    // if row is selected set string to url and send through segue to web view
    if ([[segue identifier] isEqualToString:@"showDetail"])
    {
        
        NSIndexPath *indexPath = [self.tableView indexPathForSelectedRow];
        NSString *string = [feeds[indexPath.row] objectForKey: @"link"];
        [[segue destinationViewController] setUrl:string];
        
    }
}



@end
