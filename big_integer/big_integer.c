#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
#include "big_integer.h"

#define MODE 10000

/*============private declaration==========*/
static big_integer_t *plus(big_integer_t *a, big_integer_t *b);
static big_integer_t *minus(big_integer_t *a, big_integer_t *b);
static big_integer_t *multiply(big_integer_t *a, big_integer_t *b);
static big_integer_t *divide(big_integer_t *a, big_integer_t *b);

/*============private implementation==========*/
static big_integer_t *plus(big_integer_t *a, big_integer_t *b){
	if(a == NULL || b == NULL){
		return NULL;
	}

	big_integer_t *t;
	if(a->is_plus == 0 && b->is_plus == 1){
		return minus(b, a);
	}

	if(a->is_plus == 1 && b->is_plus == 0){
		return minus(a, b);
	}

	if(a->is_plus == 0 && b->is_plus == 0){
		a->is_plus = 1;
		b->is_plus = 1;
		t = plus(a, b);
		t->is_plus = 0;
		return t;
	}

	int i = 0;
	int remain = 0;
	int tmp = 0;
	for(; i < a->length || i < b->length; i++){
		tmp = (a->value[i] + b->value[i] + remain) % MODE;
		remain = (a->value[i] + b->value[i] + remain) / MODE;
		a->value[i] = tmp;
	}

	if(remain != 0){
		a->value[i++] = remain;
	}

	a->length = i;
}

static big_integer_t *minus(big_integer_t *a, big_integer_t *b){
	if(a == NULL || b == NULL){
		return NULL;
	}

	if(a->length == 0){
		b->is_plus = 0;
		return b;
	}

	if(b->length == 0){
		return a;
	}

	if(a->is_plus == 0 && b->is_plus == 1){
		return minus(b, a);
	}

	big_integer_t *t;
	if(a->is_plus == 0 && b->is_plus == 0){
		a->is_plus = 1;
		b->is_plus = 1;
		t = plus(a, b);
		t->is_plus = 0;
		return t;
	}

	if(a->is_plus == 1 && b->is_plus == 0){
		b->is_plus = 1;
		return plus(a, b);
	}

	if(a->length < b->length || (a->value[a->length - 1] < b->value[b->length - 1])){
		t = a;
		a = b;
		b = t;
		a->is_plus = 0;
	}

	int remain = 0;
	int i = 0;
	int is_zero = 1;
	for(; i < a->length; i++){
		if(a->value[i] + remain >= b->value[i]){
			a->value[i] = a->value[i] + remain - b->value[i];
			remain = 0;
		}else{
			a->value[i] = a->value[i] + remain + MODE - b->value[i];
			remain = -1;
		}
		if(a->value[i] != 0){
			is_zero = 0;
		}
	}

	if(is_zero == 1){
		a->length = 1;
	}
	return a;
}

static big_integer_t *multiply(big_integer_t *a, big_integer_t *b){
	if(a == NULL || b == NULL){
		return NULL;
	}

	//printf("a is %d b is %d\n", a->is_plus, b->is_plus);
	a->is_plus = !(a->is_plus ^ b->is_plus);

	int remain = 0;
	int tmp;
	int is_zero = 1;
	int *result_num = (int *)malloc(sizeof(int) * (a->length + b->length - 1) * b->length);
	if(result_num == NULL){
		//TODO
	}
	memset(result_num, 0, sizeof(int) * (a->length + b->length - 1) * b->length);
//	printf("a.length = %d b length = %d\n", a->length, b->length);
	int i = 0;
	int j = 0;
	for(; i < b->length; i++){
		for(j = 0; j < a->length; j++){
			result_num[i * (a->length + b->length - 1) + j + i] = a->value[j] * b->value[i];
//			printf("i = %d j = %d tmp v1alue = %d\n", i, j, a->value[j] * b->value[i]);
		}
	}

	int times = a->length + b->length - 1;
	int sum;
	for(i = 0; i < times; i++){
		sum = 0;
		for(j = 0; j < b->length; j++){
			sum += result_num[j * (a->length + b->length - 1) + i];
		}

		a->value[i] = (sum + remain) % MODE;
//		printf("tmp value = %d\n", a->value[i]);
		if(a->value[i] != 0){
//			printf("tmp value = %d\n", a->value[i]);
			is_zero = 0;
		}
		remain = (sum + remain) / MODE;
	}

	if(remain != 0){
		a->value[i++] = remain;
		is_zero = 0;
	}

	a->length = i;

	if(is_zero == 1){
		a->length = 1;
	}

	free(result_num);
	return a;
}

static big_integer_t *divide(big_integer_t *a, big_integer_t *b){
	return NULL;
}

static int get_int(char *c, int len){
	int r = 0;
	while(len--){
		r = (r << 3) + (r << 1) + (*c++ - '0');
	}
	return r;
}

static void c_to_integer(char *c, big_integer_t *this){
	this->is_plus = 1;
	char *p = c;
	int len = strlen(c);
	if(*p == '-'){
		this->is_plus = 0;
		len--;
		p++;
	}

	while(*p == '0'){
		p++;
		len--;
	}

	this->length = ceil((double) len / 4);
	int i = this->length - 1;
	for(; i >= 0; i--){
		if(i == this->length - 1){
			if(len % 4 != 0)
				this->value[i] = get_int(p, len % 4);
			else
				this->value[i] = get_int(p, 4);
			p = p + (len % 4);
			//printf("value = %d len = %d\n", this->value[i], len % 4);
		}else{
			this->value[i] = get_int(p, 4);
			p = p + 4;
			//printf("value = %d len = %d\n", this->value[i], 4);
		}

	}
}

/*==========API Implementation==========*/
big_integer_t *create_big_integer(char *integer){

	big_integer_t *this = (big_integer_t *)malloc(sizeof(big_integer_t));
	if(this == NULL){
		return NULL;
	}

	this->op = (integer_op_t *)malloc(sizeof(integer_op_t));
	if(this->op == NULL){
		free(this);
		return NULL;
	}

	this->length = 0;
	int i = 0;
	do{
		this->value[i] = 0;
	}while(++i < BIT);

	c_to_integer(integer, this);

	this->op->plus = plus;
	this->op->minus = minus;
	this->op->multiply = multiply;
	this->op->divide = divide;

	return this;
}

void destroy_integer(big_integer_t *integer){
	if(integer == NULL){
		return;
	}

	free(integer->op);
	integer->op = NULL;
	free(integer);
	integer = NULL;
}

void print_integer(big_integer_t *integer){
	if(integer == NULL){
		puts("NULL");
		return;
	}

	puts("======START PRINT BIG_INTEGER==========");
	if(integer->is_plus == 0){
		printf("-");
	}

	int i = integer->length - 1;
	for(; i >= 0; i--){
		if(i == integer->length - 1){
			printf("%d", integer->value[i]);
		}else{
			printf("%04d", integer->value[i]);
		}
	}
	puts("");
	puts("======END PRINT BIG_INTEGER==========");
}
