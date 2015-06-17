/*
 * main.c
 *
 *  Created on: 2015年6月17日
 *      Author: ron
 */
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "big_integer.h"

int get_op_type(const char *op){
	if(strcmp(op, "+") == 0){
		return 1;
	}else if(strcmp(op, "-") == 0){
		return 2;
	}else if(strcmp(op, "*") == 0){
		return 3;
	}else if(strcmp(op, "/") == 0){
		return 4;
	}
	return 5;
}

void calculate(char *a, char *b, char *op){
	int op_type = get_op_type(op);
	if(op_type == 5){
		puts("illegal operator");
		return;
	}

	big_integer_t *integer_a = create_big_integer(a);
	big_integer_t *integer_b = create_big_integer(b);
//	print_integer(integer_a);
//	print_integer(integer_b);
	big_integer_t *result;
	switch(op_type){
		case 1:
			result = integer_a->op->plus(integer_a, integer_b);
			break;
		case 2:
			result = integer_a->op->minus(integer_a, integer_b);
			break;
		case 3:
			result = integer_a->op->multiply(integer_a, integer_b);
			break;
		case 4:
			result = integer_a->op->divide(integer_a, integer_b);
			break;
		default:
			result =  NULL;
	}
	print_integer(result);
	destroy_integer(integer_a);
	destroy_integer(integer_b);
}

//char c[1000] = "900000012111";
int main(){
	//big_integer_t *integer_b = create_big_integer(c);
	//print_integer(integer_b);

//	freopen("num.txt", "r", stdin);
	char *a = (char *)malloc(sizeof(1000));
	char *b= (char *)malloc(sizeof(1000));
	char *op = (char *)malloc(sizeof(1));
	while(1){
		puts("please enter first number:");
		scanf("%s", a);
		puts("please enter operator:");
		scanf("%s", op);
		puts("please enter second number:");
		scanf("%s", b);
		calculate(a, b, op);
	}
	return 0;
}
