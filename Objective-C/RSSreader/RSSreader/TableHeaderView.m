//
//  TableHeaderView.m
//  RSSreader
//
//  Created by Joshua O'Steen on 4/28/13.
//  Copyright (c) 2013 Joshua O'Steen. All rights reserved.
//

#import "TableHeaderView.h"

@interface TableHeaderView()
{
    // class declaration //
    UILabel* label;
}
@end

@implementation TableHeaderView

// method to format header string // 
- (id)initWithText:(NSString*)text
{
    UIImage *img = [UIImage imageNamed:@"header.png"];
    if ((self = [super initWithImage:img]))
    {
        
        // Initialization code to format label // 
        label = [[UILabel alloc] initWithFrame:CGRectMake(20,10,200,70)];
        label.textColor = [UIColor whiteColor];
        label.shadowColor = [UIColor grayColor];
        label.shadowOffset = CGSizeMake(1, 1);
        label.backgroundColor = [UIColor clearColor];
        label.font = [UIFont systemFontOfSize:20];
        label.text = text;
        label.numberOfLines = 2;
        [self addSubview:label];
    }
    
    return self;
}

// method to assign text to label // 
- (void)setText:(NSString*)text
{
    label.text = text;
}

@end
