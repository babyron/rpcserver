/*
 * big_integer.h
 *
 *  Created on: 2015年6月17日
 *      Author: ron
 */

#ifndef BIG_INTEGER_H_
#define BIG_INTEGER_H_
#define BIT 500

struct big_integer{
	int length;
	int is_plus;
	int value[BIT];
	struct integer_op *op;
};

struct integer_op{
	struct big_integer *(*plus)(struct big_integer *a, struct big_integer *b);
	struct big_integer *(*minus)(struct big_integer *a, struct big_integer *b);
	struct big_integer *(*multiply)(struct big_integer *a, struct big_integer *b);
	struct big_integer *(*divide)(struct big_integer *a, struct big_integer *b);
};

typedef struct big_integer big_integer_t;
typedef struct integer_op integer_op_t;

big_integer_t *create_big_integer(char *integer);
void destroy_integer(big_integer_t *integer);
void print_integer(big_integer_t *integer);
#endif /* BIG_INTEGER_H_ */
