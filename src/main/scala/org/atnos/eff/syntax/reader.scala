package org.atnos.eff.syntax

import cats.data.Reader
import org.atnos.eff.Tag._
import org.atnos.eff._

object reader extends reader

trait reader {

  implicit class ReaderEffectOps[R <: Effects, A](e: Eff[R, A]) {

    def runReader[C, U <: Effects](c: C)(implicit member: Member.Aux[Reader[C,  ?], R, U]): Eff[U, A] =
      ReaderInterpretation.runReader(c)(e)

    def runReaderTagged[C, U <: Effects, T](c: C)(implicit member: Member.Aux[({type l[X] = Reader[C, X] @@ T})#l, R, U]): Eff[U, A] =
      ReaderInterpretation.runReaderTagged(c)(e)
  }

}

