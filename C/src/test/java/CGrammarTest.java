import junit.framework.Assert;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;

public class CGrammarTest {


    @Test
    public void testSimpleC() throws IOException {
        final Compiler compiler = new Compiler();
        final ParseTree ast = compiler.parse("/* Airport simulation */\n" +
                "\n" +
//                "#include <stdio.h>\n" +
//                "#include <conio.h>\n" +
//                "#include <stdlib.h>\n" +
//                "#include <ctype.h>\n" +
//                "#include <math.h>\n" +
//                "#include <time.h>\n" +
//                "#include <limits.h>\n" +
//                "#include <windows.h>\n" +
//                "\n" +
//                "#define MAX 3\n" +
//                "#define ARRIVE 0\n" +
//                "#define DEPART 1\n" +
//                "\n" +
                "typedef struct\n" +
                "{\n" +
                "\tint id ;\n" +
                "\tint tm ;\n" +
                "} plane;\n" +
                "\n" +
                "typedef struct\n" +
                "{\n" +
                "\tint count ;\n" +
                "\tint front ;\n" +
                "\tint rear ;\n" +
                "   \tplane p[MAX] ;\n" +
                "} queue;\n" +
                "\n" +
                "void initqueue (  queue * ) ;\n" +
                "void addqueue (  queue *,  plane ) ;\n" +
                "plane delqueue (  queue * ) ;\n" +
                "int size (  queue ) ;\n" +
                "int empty (  queue ) ;\n" +
                "int full (  queue ) ;\n" +
                "\n" +
                "void initqueue (  queue *pq )\n" +
                "{\n" +
                "\tpq -> count = 0 ;\n" +
                "\tpq -> front = 0 ;\n" +
                "\tpq -> rear = -1 ;\n" +
                "}\n" +
                "\n" +
                "void addqueue (  queue *pq,  plane item )\n" +
                "{\n" +
                "\tif ( pq -> count >= MAX )\n" +
                "\t{\n" +
                "\t\tprintf ( \"\\nQueue is full.\\n\" ) ;\n" +
                "\t\treturn ;\n" +
                "\t}\n" +
                "\t( pq -> count )++ ;\n" +
                "\n" +
                "\tpq -> rear = ( pq -> rear + 1 ) % MAX ;\n" +
                "\tpq -> p[pq -> rear] = item ;\n" +
                "}\n" +
                "\n" +
                "plane delqueue (  queue *pq )\n" +
                "{\n" +
                "\t plane p1 ;\n" +
                "\n" +
                "\tif ( pq -> count <= 0 )\n" +
                "\t{\n" +
                "\t\tprintf ( \"\\nQueue is empty.\\n\" ) ;\n" +
                "\t\tp1.id = 0 ;\n" +
                "\t\tp1.tm = 0 ;\n" +
                "\t}\n" +
                "\telse\n" +
                "\t{\n" +
                "\t\t( pq -> count )-- ;\n" +
                "\t\tp1 = pq -> p[pq -> front] ;\n" +
                "\t\tpq -> front = ( pq -> front + 1 ) % MAX ;\n" +
                "\t}\n" +
                "\treturn p1 ;\n" +
                "}\n" +
                "\n" +
                "int size (  queue q )\n" +
                "{\n" +
                "\treturn q.count ;\n" +
                "}\n" +
                "\n" +
                "int empty (  queue q )\n" +
                "{\n" +
                "\treturn ( q.count <= 0 ) ;\n" +
                "}\n" +
                "\n" +
                "int full (  queue q )\n" +
                "{\n" +
                "\treturn ( q.count >= MAX ) ;\n" +
                "}\n" +
                "\n" +
                "typedef struct\n" +
                "{\n" +
                "\t queue landing ;\n" +
                "\t queue takeoff ;\n" +
                "\t queue *pl ;\n" +
                "\t queue *pt ;\n" +
                "\tint idletime ;\n" +
                "\tint landwait, takeoffwait ;\n" +
                "\tint nland, nplanes, nrefuse, ntakeoff ;\n" +
                "\t plane pln ;\n" +
                "} airport ;\n" +
                "\n" +
                "void initairport (  airport * ) ;\n" +
                "void start ( int *, double *, double * ) ;\n" +
                "void newplane (  airport *, int, int ) ;\n" +
                "void refuse (  airport *, int ) ;\n" +
                "void land (  airport *,  plane, int ) ;\n" +
                "void fly (  airport *,  plane, int ) ;\n" +
                "void idle (  airport *, int ) ;\n" +
                "void conclude (  airport *, int ) ;\n" +
                "int randomnumber ( double ) ;\n" +
                "void apaddqueue (  airport *, char ) ;\n" +
                " plane apdelqueue (  airport *, char ) ;\n" +
                "int apsize (  airport, char ) ;\n" +
                "int apfull (  airport, char ) ;\n" +
                "int apempty (  airport, char ) ;\n" +
                "void myrandomize ( ) ;\n" +
                "\n" +
                "void initairport (  airport *ap )\n" +
                "{\n" +
                "    initqueue ( &( ap-> landing ) ) ;\n" +
                "    initqueue ( &( ap -> takeoff ) ) ;\n" +
                "\n" +
                "\tap -> pl = &( ap -> landing ) ;\n" +
                "\tap -> pt = &( ap -> takeoff ) ;\n" +
                "\tap -> nplanes = ap -> nland = ap -> ntakeoff = ap -> nrefuse = 0 ;\n" +
                "\tap -> landwait = ap -> takeoffwait = ap -> idletime = 0 ;\n" +
                "}\n" +
                "\n" +
                "void start ( int *endtime, double *expectarrive, double *expectdepart )\n" +
                "{\n" +
                "\tint flag = 0 ;\n" +
                "\tchar wish ;\n" +
                "\n" +
                "\tprintf ( \"\\nProgram that simulates an airport with only one runway.\\n\" ) ;\n" +
                "\tprintf ( \"One plane can land or depart in each unit of time.\\n\" ) ;\n" +
                "\tprintf ( \"Up to %d planes can be waiting to land or take off at any time.\\n\", MAX ) ;\n" +
                "\tprintf ( \"How many units of time will the simulation run?\" ) ;\n" +
                "\tscanf ( \"%d\", endtime ) ;\n" +
                "\tmyrandomize( ) ;\n" +
                "\tdo\n" +
                "\t{\n" +
                "\t\tprintf ( \"\\nExpected number of arrivals per unit time? \" ) ;\n" +
                "\t\tscanf ( \"%lf\", expectarrive ) ;\n" +
                "\t\tprintf ( \"\\nExpected number of departures per unit time? \" ) ;\n" +
                "\t\tscanf ( \"%lf\", expectdepart ) ;\n" +
                "\n" +
                "\t\tif ( *expectarrive < 0.0 || *expectdepart < 0.0 )\n" +
                "\t\t{\n" +
                "\t\t\tprintf ( \"These numbers must be nonnegative.\\n\" ) ;\n" +
                "\t\t\tflag = 0 ;\n" +
                "\t\t}\n" +
                "\t\telse\n" +
                "\t\t{\n" +
                "\t\t\tif ( *expectarrive + *expectdepart > 1.0 )\n" +
                "\t\t\t{\n" +
                "\t\t\t\tprintf ( \"The airport will become saturated. Read new numbers? \" ) ;\n" +
                "                fflush ( stdin ) ;\n" +
                "\t\t\t\tscanf ( \"%c\", &wish ) ;\n" +
                "\t\t\t\tif ( tolower ( wish ) == 'y' )\n" +
                "\t\t\t\t\tflag = 0 ;\n" +
                "\t\t\t\telse\n" +
                "\t\t\t\t\tflag = 1 ;\n" +
                "\t\t\t}\n" +
                "\t\t\telse\n" +
                "\t\t\t\tflag = 1 ;\n" +
                "\t\t}\n" +
                "\t} while ( flag == 0 ) ;\n" +
                "}\n" +
                "\n" +
                "void newplane (  airport *ap, int curtime, int action )\n" +
                "{\n" +
                "\t( ap -> nplanes )++ ;\n" +
                "\tap -> pln.id = ap -> nplanes ;\n" +
                "\tap -> pln.tm = curtime ;\n" +
                "\n" +
                "\tswitch ( action )\n" +
                "\t{\n" +
                "\t\tcase ARRIVE :\n" +
                "\t\t\tprintf ( \"\\n\" ) ;\n" +
                "\t\t\tprintf ( \"Plane %d ready to land.\\n\", ap -> nplanes ) ;\n" +
                "\t\t\tbreak ;\n" +
                "\n" +
                "\t\tcase DEPART :\n" +
                "\t\t\tprintf ( \"\\nPlane %d ready to take off.\\n\", ap -> nplanes ) ;\n" +
                "\t\t\tbreak ;\n" +
                "\t}\n" +
                "}\n" +
                "\n" +
                "void refuse (  airport *ap, int action )\n" +
                "{\n" +
                "\tswitch ( action )\n" +
                "\t{\n" +
                "\t\tcase ARRIVE :\n" +
                "\n" +
                "\t\t\t printf ( \"\\tplane  %d directed to another airport.\\n\", ap -> pln.id ) ;\n" +
                "\t\t\t break ;\n" +
                "\n" +
                "\t\tcase DEPART :\n" +
                "\n" +
                "\t\t\t printf ( \"\\tplane %d told to try later.\\n\", ap -> pln.id ) ;\n" +
                "\t\t\t break ;\n" +
                "\t}\n" +
                "\t( ap -> nrefuse )++ ;\n" +
                "}\n" +
                "\n" +
                "void land (  airport *ap,  plane pl, int curtime )\n" +
                "{\n" +
                "\tint wait ;\n" +
                "\n" +
                "\twait = curtime - pl.tm ;\n" +
                "\tprintf ( \"%d: Plane %d landed \", curtime, pl.id ) ;\n" +
                "\tprintf ( \"in queue %d units \\n\", wait ) ;\n" +
                "\t( ap -> nland ) ++ ;\n" +
                "\t( ap -> landwait ) += wait ;\n" +
                "}\n" +
                "\n" +
                "void fly (  airport *ap,  plane pl, int curtime )\n" +
                "{\n" +
                "\tint wait ;\n" +
                "\n" +
                "\twait = curtime - pl.tm ;\n" +
                "\tprintf ( \"%d: Plane %d took off \", curtime, pl.id ) ;\n" +
                "\tprintf ( \"in queue %d units \\n\", wait ) ;\n" +
                "\t( ap -> ntakeoff )++ ;\n" +
                "\t( ap -> takeoffwait ) += wait ;\n" +
                "}\n" +
                "\n" +
                "void idle (  airport *ap, int curtime )\n" +
                "{\n" +
                "\tprintf ( \"%d: Runway is idle.\\n\", curtime ) ;\n" +
                "\tap -> idletime++ ;\n" +
                "}\n" +
                "\n" +
                "void conclude (  airport *ap, int endtime )\n" +
                "{\n" +
                "\tprintf ( \"\\tSimulation has concluded after %d units.\\n\", endtime ) ;\n" +
                "\tprintf ( \"\\tTotal number of planes processed: %d\\n\", ap -> nplanes ) ;\n" +
                "\tprintf ( \"\\tNumber of planes landed: %d\\n\", ap -> nland ) ;\n" +
                "\tprintf ( \"\\tNumber of planes taken off: %d\\n\", ap -> ntakeoff ) ;\n" +
                "\tprintf ( \"\\tNumber of planes refused use: %d\\n\", ap -> nrefuse ) ;\n" +
                "\tprintf ( \"\\tNumber left ready to land: %d\\n\", apsize ( *ap, 'l' ) ) ;\n" +
                "\tprintf ( \"\\tNumber left ready to take off: %d\\n\", apsize ( *ap, 't' ) ) ;\n" +
                "\n" +
                "\tif ( endtime > 0 )\n" +
                "\t\tprintf ( \"\\tPercentage of time runway idle: %lf \\n\", ( ( double ) ap -> idletime / endtime ) * 100 ) ;\n" +
                "\n" +
                "\tif ( ap -> nland > 0 )\n" +
                "\t\tprintf ( \"\\tAverage wait time to land: %lf \\n\", ( ( double ) ap -> landwait / ap -> nland ) ) ;\n" +
                "\n" +
                "\tif ( ap -> ntakeoff > 0 )\n" +
                "\t\tprintf ( \"\\tAverage wait time to take off: %lf \\n\", ( ( double ) ap -> takeoffwait / ap -> ntakeoff ) ) ;\n" +
                "}\n" +
                "\n" +
                "int randomnumber ( double expectedvalue )\n" +
                "{\n" +
                "\tint n = 0 ;\n" +
                "\tdouble em ;\n" +
                "\tdouble x ;\n" +
                "\n" +
                "\tem = exp ( -expectedvalue ) ;\n" +
                "\tx = rand( ) / ( double ) INT_MAX ;\n" +
                "\n" +
                "\twhile ( x > em )\n" +
                "\t{\n" +
                "\t\tn++ ;\n" +
                "\t\tx *= rand( ) / ( double ) INT_MAX ;\n" +
                "\t}\n" +
                "\n" +
                "\treturn n ;\n" +
                "}\n" +
                "\n" +
                "void apaddqueue (  airport *ap, char type )\n" +
                "{\n" +
                "\tswitch ( tolower( type ) )\n" +
                "\t{\n" +
                "\t\tcase 'l' :\n" +
                "\t\t\t  addqueue ( ap -> pl, ap -> pln ) ;\n" +
                "\t\t\t  break ;\n" +
                "\n" +
                "\t\tcase 't' :\n" +
                "\t\t\t  addqueue ( ap -> pt, ap -> pln ) ;\n" +
                "\t\t\t  break ;\n" +
                "\t}\n" +
                "}\n" +
                "\n" +
                " plane apdelqueue (  airport *ap, char type )\n" +
                "{\n" +
                "\t plane p1 ;\n" +
                "\n" +
                "\tswitch ( tolower ( type ) )\n" +
                "\t{\n" +
                "\t\tcase 'l' :\n" +
                "\t\t\t  p1 = delqueue ( ap -> pl ) ;\n" +
                "\t\t\t  break ;\n" +
                "\n" +
                "\t\tcase 't' :\n" +
                "\t\t\t  p1 = delqueue ( ap -> pl ) ;\n" +
                "\t\t\t  break ;\n" +
                "\t}\n" +
                "\n" +
                "\treturn p1 ;\n" +
                "}\n" +
                "\n" +
                "int apsize (  airport ap, char type )\n" +
                "{\n" +
                "\tswitch ( tolower ( type ) )\n" +
                "\t{\n" +
                "\t\tcase 'l' :\n" +
                "\t\t\t  return ( size ( *( ap.pl ) ) ) ;\n" +
                "\n" +
                "\t\tcase 't' :\n" +
                "\t\t\t  return ( size ( *( ap.pt ) ) ) ;\n" +
                "\t}\n" +
                "\n" +
                "\treturn 0 ;\n" +
                "}\n" +
                "\n" +
                "int apfull (  airport ap, char type )\n" +
                "{\n" +
                "\tswitch ( tolower ( type ) )\n" +
                "\t{\n" +
                "\t\tcase 'l' :\n" +
                "\t\t\t  return ( full ( *( ap.pl ) ) ) ;\n" +
                "\n" +
                "\t\tcase 't' :\n" +
                "\t\t\t  return ( full ( *( ap.pt ) ) ) ;\n" +
                "\t}\n" +
                "\n" +
                "\treturn 0 ;\n" +
                "}\n" +
                "\n" +
                "int apempty (  airport ap, char type )\n" +
                "{\n" +
                "\tswitch ( tolower ( type ) )\n" +
                "\t{\n" +
                "\t\tcase 'l' :\n" +
                "\t\t\t  return ( empty ( *( ap.pl ) ) ) ;\n" +
                "\n" +
                "\t\tcase 't' :\n" +
                "\t\t\t  return ( empty ( *( ap.pt ) ) ) ;\n" +
                "\t}\n" +
                "\n" +
                "\treturn 0 ;\n" +
                "}\n" +
                "\n" +
                "void myrandomize( )\n" +
                "{\n" +
                "\tsrand ( ( unsigned int ) ( time ( NULL ) % 10000 ) ) ;\n" +
                "}\n" +
                "\n" +
                "int main( )\n" +
                "{\n" +
                "\t airport a ;\n" +
                "\tint i, pri, curtime, endtime ;\n" +
                "\tdouble expectarrive, expectdepart ;\n" +
                "\t plane temp ;\n" +
                "\n" +
                "\n" +
                "    initairport ( &a );\n" +
                "\n" +
                "\tstart ( &endtime, &expectarrive, &expectdepart ) ;\n" +
                "\n" +
                "\tfor ( curtime = 1 ; curtime <= endtime ; curtime++ )\n" +
                "\t{\n" +
                "\t\tpri = randomnumber ( expectarrive ) ;\n" +
                "\n" +
                "\t\tfor ( i = 1 ; i <= pri ; i++ )\n" +
                "\t\t{\n" +
                "\t\t\tnewplane ( &a, curtime, ARRIVE ) ;\n" +
                "\t\t\tif ( apfull ( a, 'l' ) )\n" +
                "\t\t\t\t refuse ( &a, ARRIVE ) ;\n" +
                "\t\t\telse\n" +
                "\t\t\t\tapaddqueue( &a, 'l' ) ;\n" +
                "\t\t}\n" +
                "\n" +
                "\t\tpri = randomnumber ( expectdepart ) ;\n" +
                "\t\tfor ( i = 1 ; i <= pri ; i++ )\n" +
                "\t\t{\n" +
                "\t\t\tnewplane ( &a, curtime, DEPART ) ;\n" +
                "\t\t\tif ( apfull ( a, 't' ) )\n" +
                "\t\t\t   refuse ( &a, DEPART ) ;\n" +
                "\t\t\telse\n" +
                "\t\t\t   apaddqueue ( &a, 't' ) ;\n" +
                "\t\t}\n" +
                "\n" +
                "\t\tif (  ! ( apempty ( a, 'l' ) ) )\n" +
                "\t\t{\n" +
                "\t\t\ttemp = apdelqueue ( &a, 'l' ) ;\n" +
                "\t\t\tland ( &a, temp, curtime ) ;\n" +
                "\t\t}\n" +
                "\t\telse\n" +
                "\t\t{\n" +
                "\t\t\tif ( ! ( apempty ( a, 't' ) ) )\n" +
                "\t\t\t{\n" +
                "\t\t\t\ttemp = apdelqueue ( &a, 't' ) ;\n" +
                "\t\t\t\tfly ( &a, temp, curtime ) ;\n" +
                "\t\t\t}\n" +
                "\t\t\telse\n" +
                "\t\t\t\tidle ( &a, curtime ) ;\n" +
                "\t\t}\n" +
                "\t}\n" +
                "\n" +
                "\tconclude ( &a, endtime ) ;\n" +
                "\n" +
                "    return 0 ;\n" +
                "}\n", false);

        final TreePrinterListener treePrinterListener = new TreePrinterListener(compiler.getParser());
        ParseTreeWalker.DEFAULT.walk(treePrinterListener, ast);
        final String formatted = treePrinterListener.toString();
        System.out.println(formatted);
        System.out.println(ast);
        Assert.assertNotNull(ast);
    }
}
